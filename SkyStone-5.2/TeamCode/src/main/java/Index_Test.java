import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="INDEX_TEST", group="AUTON")
public class Index_Test extends LinearOpMode {
    Utilities robot = new Utilities();
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor top_right = null;
    private DcMotor top_left = null;
    private DcMotor bottom_right = null;
    private DcMotor bottom_left = null;
    private DcMotor arm = null;
    private DcMotor arm2 = null;
    private DcMotor grabber = null;

    @Override
    public void runOpMode() {
        top_right = hardwareMap.get(DcMotor.class, "TR");
        top_left = hardwareMap.get(DcMotor.class, "TL");
        bottom_right = hardwareMap.get(DcMotor.class, "BR");
        bottom_left = hardwareMap.get(DcMotor.class, "BL");
        arm = hardwareMap.get(DcMotor.class, "A");
        arm2 = hardwareMap.get(DcMotor.class, "A2");
        grabber = hardwareMap.get(DcMotor.class, "G");

        top_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        top_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        top_left.setTargetPosition(0);
        top_right.setTargetPosition(0);
        bottom_left.setTargetPosition(0);
        bottom_right.setTargetPosition(0);
        top_right.setDirection(DcMotor.Direction.FORWARD);
        top_left.setDirection(DcMotor.Direction.REVERSE);
        bottom_right.setDirection(DcMotor.Direction.FORWARD);
        bottom_left.setDirection(DcMotor.Direction.REVERSE);
        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        double test = robot.index(top_left, top_right, bottom_left, bottom_right, 24, 96, 0.25, 0.25, 0.0);
        telemetry.addData("Dist", test);
        telemetry.update();
        sleep(10000);
    }
    public double index (DcMotor tl, DcMotor tr, DcMotor bl, DcMotor br, double inches_per_second, double total_distance_in, double accel_percent, double decel_percent, double angle) {
        // profile state definitions
        final int INIT = 0;
        final int ACCEL = 1;
        final int CONST = 2;
        final int DECEL = 3;
        final int DONE = 4;
        int TIC = 10; // update rate in ms
        int state = INIT; // first case
        double accel_dist_enc = 0;
        double decel_dist_enc = 0;
        double end_const_dist_enc = 0;
        double total_dist_enc = robot.inch_per_enc(total_distance_in);
        double enc_cnts_per_TIC = robot.inch_per_enc(inches_per_second) * (TIC / 1000.0);
        double position = 0.0;
        double speed = 0.0;
        double accel_slope = 0.0;
        double decel_slope = 0.0;
        int a = 0;
        int b = 0;
        double accel_time = 0.0;
        double decel_time = 0.0;
        double sign = 1;
        int count = 0;

        while (state != DONE) {
            switch (state) {
                case INIT:
                    if (total_distance_in < 0)
                        sign = -1;
                    total_dist_enc = total_dist_enc * sign;
                    if (angle == 1.0){ //right
                        a = 1;
                        b = -1;
                    }
                    else if (angle == 2.0){ //left
                        a = -1;
                        b = 1;
                    }
                    else{
                        a = 1;
                        b = 1;
                    }

                    //distance in encoder counts for the accel and decel portions of the movement
                    accel_dist_enc = (accel_percent * total_dist_enc);
                    decel_dist_enc = (decel_percent * total_dist_enc);

                    //time for accel in TIC and the slope is in encoder counts per TIC squared
                    accel_time = (2.0 * accel_dist_enc) / enc_cnts_per_TIC;
                    accel_slope = enc_cnts_per_TIC / accel_time;

                    //time for decel in TIC and the slope is in encoder counts per TIC squared
                    decel_time = (2.0 * decel_dist_enc) / enc_cnts_per_TIC;
                    decel_slope = enc_cnts_per_TIC / decel_time;

                    //the end of the constant speed portion in encoder counts
                    end_const_dist_enc = total_dist_enc - decel_dist_enc;
                    //switch to next state
                    tl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    tr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    state = ACCEL;
                    break;
                case ACCEL:
                    speed = speed + accel_slope;//add accel_slope to speed
                    position = position + speed;
                    //add speed to position
                    if (position >= accel_dist_enc) //check to make sure position is actually at its desired finish
                        //position = accel_dist_enc;
                        //switch to next state
                        state = CONST;
                    break;
                case CONST:
                    position = position + enc_cnts_per_TIC;
                    speed = enc_cnts_per_TIC;
                    if (position >= end_const_dist_enc) //check to make sure position is actually at its desired finish
                        //position = end_const_dist_enc;
                        //switch to next state
                        state = DECEL;
                    break;
                case DECEL:
                    speed = speed - decel_slope;//subtract decel_slope from speed
                    position = position + speed;//add speed to position
                    if ((position >= total_dist_enc) || (speed <= 0.0)){ //check to make sure position is actually at its desired finish
                        position = total_dist_enc;
                        //switch to last state
                        state = DONE;}
                    break;
                case DONE:
                    break;
            }
            tl.setTargetPosition((int)(position*sign*b));
            tr.setTargetPosition((int)(position*sign*a));
            bl.setTargetPosition((int)(position*sign*a));
            br.setTargetPosition((int)(position*sign*b));
            tl.setPower(1.0);
            tr.setPower(1.0);
            bl.setPower(1.0);
            br.setPower(1.0);
            sleep((long)TIC);
            count = count+1;
        }
        return(count);
    }
}
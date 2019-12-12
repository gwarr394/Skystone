import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="BLUE_FOUND", group="AUTON")
@Disabled
public class Auton2 extends LinearOpMode{
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
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        top_left.setTargetPosition(0);
        top_right.setTargetPosition(0);
        bottom_left.setTargetPosition(0);
        bottom_right.setTargetPosition(0);
        top_left.setDirection(DcMotor.Direction.FORWARD);
        top_right.setDirection(DcMotor.Direction.REVERSE);
        bottom_left.setDirection(DcMotor.Direction.FORWARD);
        bottom_right.setDirection(DcMotor.Direction.REVERSE);
        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        robot.index(top_left, top_right, bottom_left, bottom_right, 12, 12.625, 0.25, 0.25, 2.0);
        sleep(1000);

        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.index(top_left, top_right, bottom_left, bottom_right, 20, 29, 0.25, 0.25, 0.0);
        sleep(1000);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(robot.degree_per_enc(90));
        arm2.setTargetPosition(robot.degree_per_enc(90));
        arm.setPower(0.85);
        arm2.setPower(0.85);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sleep(1000);

        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.index(top_left, top_right, bottom_left, bottom_right, 20, -28, 0.25, 0.25, 0.0);
        sleep(1000);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(robot.degree_per_enc(-100));
        arm2.setTargetPosition(robot.degree_per_enc(-100));
        arm.setPower(-0.85);
        arm2.setPower(-0.85);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sleep(1000);

        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.index(top_left, top_right, bottom_left, bottom_right, 24, 48, 0.25, 0.25, 1.0);
        sleep(1000);
        //start on wall
        //move towards foundation close to wall
        //move front end of robot to foundation
        //grip onto foundation
        //pull foundation into build site
        //release grips
        //park on the line
    }
}

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AUTONOMOUS_F_P_RED", group="AUTON")
public class Auton2 extends LinearOpMode{
    Utilities robot = new Utilities();
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor top_right = null;
    private DcMotor top_left = null;
    private DcMotor bottom_right = null;
    private DcMotor bottom_left = null;
    private DcMotor arm = null;
    private DcMotor arm2 = null;
    @Override
    public void runOpMode() {
        top_right = hardwareMap.get(DcMotor.class, "TR");
        top_left = hardwareMap.get(DcMotor.class, "TL");
        bottom_right = hardwareMap.get(DcMotor.class, "BR");
        bottom_left = hardwareMap.get(DcMotor.class, "BL");
        arm = hardwareMap.get(DcMotor.class, "A");
        arm2 = hardwareMap.get(DcMotor.class, "A");
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

        top_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_left.setTargetPosition(robot.inch_per_enc(-12.625));
        top_right.setTargetPosition(robot.inch_per_enc(12.625));
        bottom_left.setTargetPosition(robot.inch_per_enc(12.625));
        bottom_right.setTargetPosition(robot.inch_per_enc(-12.625));
        top_left.setPower(-0.5);
        top_right.setPower(0.5);
        bottom_left.setPower(0.5);
        bottom_right.setPower(-0.5);
        sleep(1000);

        top_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_left.setTargetPosition(robot.inch_per_enc(-29));
        top_right.setTargetPosition(robot.inch_per_enc(-29));
        bottom_left.setTargetPosition(robot.inch_per_enc(-29));
        bottom_right.setTargetPosition(robot.inch_per_enc(-29));
        top_left.setPower(-1.0);
        top_right.setPower(-1.0);
        bottom_left.setPower(-1.0);
        bottom_right.setPower(-1.0);
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

        top_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        top_left.setTargetPosition(robot.inch_per_enc(28));
        top_right.setTargetPosition(robot.inch_per_enc(28));
        bottom_left.setTargetPosition(robot.inch_per_enc(28));
        bottom_right.setTargetPosition(robot.inch_per_enc(28));
        top_left.setPower(1.0);
        top_right.setPower(1.0);
        bottom_left.setPower(1.0);
        bottom_right.setPower(1.0);
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
        top_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_left.setTargetPosition(robot.inch_per_enc(48));
        top_right.setTargetPosition(robot.inch_per_enc(-48));
        bottom_left.setTargetPosition(robot.inch_per_enc(-48));
        bottom_right.setTargetPosition(robot.inch_per_enc(48));
        top_left.setPower(-0.5);
        top_right.setPower(0.5);
        bottom_left.setPower(0.5);
        bottom_right.setPower(-0.5);
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

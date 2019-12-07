import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AUTONOMOUS_P_ALL", group="AUTON")
public class Auton3 extends LinearOpMode{
    Utilities robot = new Utilities();
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor top_right = null;
    private DcMotor top_left = null;
    private DcMotor bottom_right = null;
    private DcMotor bottom_left = null;
    private DcMotor arm = null;
    @Override
    public void runOpMode() {
        top_right = hardwareMap.get(DcMotor.class, "TR");
        top_left = hardwareMap.get(DcMotor.class, "TL");
        bottom_right = hardwareMap.get(DcMotor.class, "BR");
        bottom_left = hardwareMap.get(DcMotor.class, "BL");
        arm = hardwareMap.get(DcMotor.class, "A");
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
        //robot.index(top_left, top_right, bottom_left, bottom_right, 18.0, 36.0, 0.25, 0.25, 0.0);
        top_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottom_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        top_left.setTargetPosition(robot.inch_per_enc(36));
        top_right.setTargetPosition(robot.inch_per_enc(36));
        bottom_left.setTargetPosition(robot.inch_per_enc(36));
        bottom_right.setTargetPosition(robot.inch_per_enc(36));
        top_left.setPower(1.0);
        top_right.setPower(1.0);
        bottom_left.setPower(1.0);
        bottom_right.setPower(1.0);
        sleep(1000);
    }
}

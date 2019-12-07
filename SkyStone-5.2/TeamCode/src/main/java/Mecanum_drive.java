import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Goodies_mecanum", group="Drive")

public class Mecanum_drive extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor top_right = null;
    private DcMotor top_left = null;
    private DcMotor bottom_right = null;
    private DcMotor bottom_left = null;
    private DcMotor arm = null;
    private DcMotor arm2 = null;
    private DcMotor grabber = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        top_right = hardwareMap.get(DcMotor.class, "TR");
        top_left = hardwareMap.get(DcMotor.class, "TL");
        bottom_right = hardwareMap.get(DcMotor.class, "BR");
        bottom_left = hardwareMap.get(DcMotor.class, "BL");
        arm = hardwareMap.get(DcMotor.class, "A");
        arm2 = hardwareMap.get(DcMotor.class, "A2");
        grabber = hardwareMap.get(DcMotor.class, "G");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        top_right.setDirection(DcMotor.Direction.FORWARD);
        top_left.setDirection(DcMotor.Direction.REVERSE);
        bottom_right.setDirection(DcMotor.Direction.FORWARD);
        bottom_left.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        arm2.setDirection(DcMotor.Direction.FORWARD);
        grabber.setDirection(DcMotor.Direction.FORWARD);
        grabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        top_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        top_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bottom_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bottom_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double vx = -44*gamepad1.left_stick_y;//16.663
        double vy = 44*gamepad1.left_stick_x;
        double w = -4*(Math.PI/3)*gamepad1.right_stick_x;
        double L1 = 5.5; //front
        double L2 = 6.125; //sides
        double R = 1.5;
        double GR = 2;
        telemetry.addData("VX", vx);
        telemetry.addData("VY", vy);
        telemetry.addData("W", w);
        double w1= (vx+vy-w*(L1+L2))/R;
        double w2= (vx-vy+w*(L1+L2))/R;
        double w3= (vx-vy-w*(L1+L2))/R;
        double w4= (vx+vy+w*(L1+L2))/R;
        top_left.setPower(w1/(15.71*GR));
        top_right.setPower(w2/(15.71*GR));
        bottom_left.setPower(w3/(15.71*GR));
        bottom_right.setPower(w4/(15.71*GR));

        if(gamepad2.dpad_up == true){
            arm.setPower(0.7);
            arm2.setPower(0.7);
        }
        else if(gamepad2.dpad_down == true){
            arm.setPower(-0.7);
            arm2.setPower(-0.7);
        }
        else{
            arm.setPower(0.0);
            arm2.setPower(0.0);
        }

        if(gamepad2.a == true){
            grabber.setPower(0.25);
        }
        else if(gamepad2.b == true){
            grabber.setPower(-0.25);
        }
        else{
            grabber.setPower(0.0);
        }
    }
}
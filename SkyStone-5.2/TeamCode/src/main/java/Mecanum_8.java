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

@TeleOp(name="Mecanum", group="Drive")

public class Mecanum_8 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor top_right = null;
    private DcMotor top_left = null;
    private DcMotor bottom_right = null;
    private DcMotor bottom_left = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        top_right  = hardwareMap.get(DcMotor.class, "TR");
        top_left = hardwareMap.get(DcMotor.class, "TL");
        bottom_right  = hardwareMap.get(DcMotor.class, "BR");
        bottom_left = hardwareMap.get(DcMotor.class, "BL");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        top_left.setDirection(DcMotor.Direction.FORWARD);
        top_right.setDirection(DcMotor.Direction.REVERSE);
        bottom_left.setDirection(DcMotor.Direction.FORWARD);
        bottom_right.setDirection(DcMotor.Direction.REVERSE);

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
        double x = gamepad1.right_stick_x;
        double y = -gamepad1.right_stick_y;
        double cs = gamepad1.right_trigger;
        double ccs = gamepad1.left_trigger;
        if(y <= 0.1 && y >= -0.1 && x <= 0.1 && x >= -0.1){
            top_left.setPower(0.0);
            top_right.setPower(0.0);
            bottom_left.setPower(0.0);
            bottom_right.setPower(0.0);
        }
        else if(y == 1.0){
            top_left.setPower(1.0);
            top_right.setPower(1.0);
            bottom_left.setPower(1.0);
            bottom_right.setPower(1.0);
        }
        else if(y == -1.0){
            top_left.setPower(-1.0);
            top_right.setPower(-1.0);
            bottom_left.setPower(-1.0);
            bottom_right.setPower(-1.0);
        }
        else if(x == 1.0){
            top_left.setPower(1.0);
            top_right.setPower(-1.0);
            bottom_left.setPower(-1.0);
            bottom_right.setPower(1.0);
        }
        else if(x == -1.0){
            top_left.setPower(-1.0);
            top_right.setPower(1.0);
            bottom_left.setPower(1.0);
            bottom_right.setPower(-1.0);
        }
        else if(y > 0.0 && x < 0.0){
            top_left.setPower(1.0);
            top_right.setPower(0.0);
            bottom_left.setPower(0.0);
            bottom_right.setPower(1.0);
        }
        else if(y > 0.0 && x > 0.0){
            top_left.setPower(0.0);
            top_right.setPower(1.0);
            bottom_left.setPower(1.0);
            bottom_right.setPower(0.0);
        }
        else if(y < 0.0 && x > 0.0){
            top_left.setPower(-1.0);
            top_right.setPower(0.0);
            bottom_left.setPower(0.0);
            bottom_right.setPower(-1.0);
        }
        else if(y < 0.0 && x < 0.0){
            top_left.setPower(0.0);
            top_right.setPower(-1.0);
            bottom_left.setPower(-1.0);
            bottom_right.setPower(0.0);
        }
        else if(cs > 0.0){
            top_left.setPower(1.0);
            top_right.setPower(-1.0);
            bottom_left.setPower(1.0);
            bottom_right.setPower(-1.0);
        }
        else if(ccs > 0.0){
            top_left.setPower(-1.0);
            top_right.setPower(1.0);
            bottom_left.setPower(-1.0);
            bottom_right.setPower(1.0);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}


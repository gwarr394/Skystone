import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Tank", group="Drive")

public class Tank extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor right = null;
    private DcMotor left = null;
    private DcMotor grab = null;
    private Servo puller = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

    right = hardwareMap.get(DcMotor.class, "R");
    left = hardwareMap.get(DcMotor.class, "L");
    grab = hardwareMap.get(DcMotor.class, "G");
    puller = hardwareMap.get(Servo.class, "P");
    right.setDirection(DcMotor.Direction.FORWARD);
    left.setDirection(DcMotor.Direction.REVERSE);

    telemetry.addData("Status", "Initialized");
    }
    @Override
    public void init_loop() {
    }
    @Override
    public void start() {
        runtime.reset();
    }
    @Override
    public void loop() {
        double r = -gamepad1.right_stick_y;
        //double l = -gamepad1.left_stick_y;
        double x_direction = gamepad1.left_stick_x;

        right.setPower(r);
        //left.setPower(l);

        double c = -gamepad1.right_trigger;
        double o = gamepad1.left_trigger;

        if(gamepad1.right_trigger > 0.0){
            grab.setPower(c);
        }
        else if(gamepad1.left_trigger > 0.0){
            grab.setPower(o);
        }
        else{
            grab.setPower(0.0);
        }

        if(gamepad1.a == true){
            puller.setPosition(0.0);
        }
        else if(gamepad1.b == true){
            puller.setPosition(0.8);
        }
        telemetry.addData("Value", x_direction);
        
    }
}

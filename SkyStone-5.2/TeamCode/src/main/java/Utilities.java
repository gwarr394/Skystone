import com.qualcomm.robotcore.hardware.DcMotor;

public class Utilities {
    private void my_sleep (long a){
        long start = System.nanoTime();
        long current;
        long elapsed = 0;
        while(elapsed < (a)*333333) {
            current = System.nanoTime();
            elapsed = current - start;
        }
    }
    /* Constructor */
    public static int degree_per_enc(double deg){
        double ENC = 1152;
        double GEAR_RATIO = 2.0/1.0;
        double ENC_ROT = ENC/GEAR_RATIO;
        return (int)(ENC_ROT*(deg/360));
    }

    /*public class index_data{
        double inches_per_second;
        double total_distance_in;
        double accel_percent;
        double decel_percent;
    }
    */


    public static int inch_per_enc(double in){
        double ENC = 1120;
        double GEAR_RATIO = 2.0/1.0;
        double DIAM_IN = 3.0;
        double CIRCUM_IN = DIAM_IN * Math.PI;
        double ENC_ROT = ENC/GEAR_RATIO;
        return (int)(ENC_ROT*(in/CIRCUM_IN));
    }
    public static int spin_inch_per_enc(double degree){
        double SPIN_DIAM_IN = 11.25;
        double SPIN_CIRCUM_IN = Math.PI * SPIN_DIAM_IN;
        double SPIN_IN = (degree/360)*SPIN_CIRCUM_IN;
        return inch_per_enc(SPIN_IN);
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
        double total_dist_enc = inch_per_enc(total_distance_in);
        double enc_cnts_per_TIC = inch_per_enc(inches_per_second) * (TIC / 1000.0);
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
            tl.setTargetPosition((int)(position*sign*a));
            tr.setTargetPosition((int)(position*sign*b));
            bl.setTargetPosition((int)(position*sign*b));
            br.setTargetPosition((int)(position*sign*a));
            tl.setPower(1.0);
            tr.setPower(1.0);
            bl.setPower(1.0);
            br.setPower(1.0);
            my_sleep((long)TIC);
            count = count+1;
        }
        return(count);
    }
    public void turn_index (DcMotor tl, DcMotor tr, DcMotor bl, DcMotor br, double inches_per_second, double degree, double accel_percent, double decel_percent, double angle) {
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
        double total_dist_enc = spin_inch_per_enc(degree);
        double enc_cnts_per_TIC = inch_per_enc(inches_per_second) * (TIC / 1000.0);
        double position = 0.0;
        double old_pos = 0;
        double speed = 0.0;
        double accel_slope = 0.0;
        double decel_slope = 0.0;

        double accel_time = 0.0;
        double decel_time = 0.0;
        double sign = 1;

        while (state != DONE) {
            switch (state) {
                case INIT:
                    if (degree < 0)
                        sign = -1;
                    total_dist_enc = total_dist_enc * sign;

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
            tl.setTargetPosition((int)(position*sign));
            tr.setTargetPosition((int)(position*-sign));
            bl.setTargetPosition((int)(position*sign));
            br.setTargetPosition((int)(position*-sign));
            tl.setPower(1.0);
            tr.setPower(1.0);
            bl.setPower(1.0);
            br.setPower(1.0);

            my_sleep((long)TIC);
        }
    }
}

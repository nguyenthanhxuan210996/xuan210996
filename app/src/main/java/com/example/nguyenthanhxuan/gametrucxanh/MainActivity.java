package com.example.nguyenthanhxuan.gametrucxanh;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int bitmap_width;
    int bitmap_height;
    public static final int topic_num=3;
    int image;
    int checkrandom[] = new int[topic_num];
    String stranswer;
    ImageView img;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    int img1,img2;
    //public static Vibrator rung;
    int row_no = 4;
    int col_no = 4;
    ChessBoard chessboard;
    Button reset;
    EditText traloi;
    String cautraloi, dapan;
    public static TextView thongbao,thoigian;


    TextView goiy;
    public static Animation fadein,blink;
    int giay=61;
    CountDownTimer time;
    int timecheck=0;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        bitmap_width=width;
        bitmap_height=width;

        //rung = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        img = (ImageView) findViewById(R.id.imageView);
        thongbao = (TextView)findViewById(R.id.thongbao);
        thoigian = (TextView)findViewById(R.id.thoigian);
        goiy=(TextView)findViewById(R.id.goiy);
        reset = (Button)findViewById(R.id.reset);
        image=R.drawable.a;
        img1=R.drawable.a;
        img2=R.drawable.banhoc;
        cautraloi = "banhoc";
        dapan = "Bàn học";

        bitmap = Bitmap.createBitmap(bitmap_width, bitmap_height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setARGB(255, 0, 0, 0);
        paint.setStrokeWidth(2);
        chessboard = new ChessBoard(MainActivity.this, canvas, paint, bitmap_width, bitmap_height, col_no, row_no, img1, img2);
        chessboard.Init();
        chessboard.drawChessBoard();
        img.setImageBitmap(bitmap);
        img.startAnimation(fadein);
        thoigian.setText(giay-1+"s");
        time = new CountDownTimer(giay*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                thoigian.setText( millisUntilFinished / 1000+"s");
            }
            public void onFinish() {
                thoigian.setText("Hết giờ!");
                thongbao.setText("Thua rồi!");
                traloi.clearAnimation();
                reset.startAnimation(blink);
                img.setEnabled(false);
                traloi.setEnabled(false);
            }
        };
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                time.cancel();
            }
        });
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                timecheck++;
                if(timecheck==1)time.start();
                if(chessboard.onTouch(v, event)==1){
                    //mở hết ô

                }
                return true;
            }
        });
    }
    public void reset(){
        reset.clearAnimation();

        img.setEnabled(true);
        traloi.setEnabled(true);
        bitmap = Bitmap.createBitmap(bitmap_width, bitmap_height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setARGB(255, 0, 0, 0);
        paint.setStrokeWidth(2);
        chessboard = new ChessBoard(MainActivity.this, canvas, paint, bitmap_width, bitmap_height, col_no, row_no, img1, img2);
        chessboard.Init();
        chessboard.drawChessBoard();
        img.setImageBitmap(bitmap);
        img.startAnimation(fadein);
        traloi.setText("");
        thongbao.setText("");
        thoigian.setText(giay-1+"s");
        timecheck=0;
    }


}

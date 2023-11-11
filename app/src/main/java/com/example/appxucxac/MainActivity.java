    package com.example.appxucxac;

    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;

    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    public class MainActivity extends Activity {
        private DatabaseReference mDatabase;
        TextView kq;
        Button getBtn;
        ImageView resultImageView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout);

            // Liên kết biến với các phần tử trong layout
            getBtn = findViewById(R.id.get);
            kq = findViewById(R.id.ketqua);
            resultImageView = findViewById(R.id.resultImageView);

            // Kết nối với Firebase Realtime Database
            mDatabase = FirebaseDatabase.getInstance().getReference();

            // Xử lý sự kiện khi nút "Get" được nhấn
            getBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Thêm lắng nghe sự kiện cho cơ sở dữ liệu Firebase
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Lấy nội dung của TextView
                            String textViewContent = snapshot.getValue().toString();

                            // Hiển thị giá trị lên TextView
                            kq.setText(textViewContent);

                            // Hiển thị ảnh tương ứng với nội dung của TextView
                            displayImage(textViewContent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Xử lý lỗi nếu có
                        }
                    });
                }
            });
        }

        // Hàm hiển thị ảnh tương ứng với nội dung của TextView
        private void displayImage(String textViewContent) {
            // Lấy ID tài nguyên hình ảnh từ res/drawable
            int imageResourceId = 0;

            // Kiểm tra nội dung của TextView và thiết lập ảnh tương ứng
            switch (textViewContent) {
                case "{DICE_STATUS=1}":
                    imageResourceId = R.drawable.mot;
                    break;
                case "{DICE_STATUS=2}":
                    imageResourceId = R.drawable.hai;
                    break;
                case "{DICE_STATUS=3}":
                    imageResourceId = R.drawable.ba;
                    break;
                case "{DICE_STATUS=4}":
                    imageResourceId = R.drawable.bon;
                    break;
                case "{DICE_STATUS=5}":
                    imageResourceId = R.drawable.nam;
                    break;
                case "{DICE_STATUS=6}":
                    imageResourceId = R.drawable.sau;
                    break;
                case "{DICE_STATUS=0}":
                    imageResourceId = R.drawable.nghieng;
                    break;
                // Thêm các trường hợp khác nếu cần
                default:
                    break;
            }

            // Thay đổi ảnh trong ImageView
            resultImageView.setImageResource(imageResourceId);
        }
    }

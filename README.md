# APIs cho Cổng thông tin của hệ thống tuyển sinh

## Introduction
- Xây dựng các API cho Cổng thông tin của hệ thống tuyển sinh

- Tuân theo chuẩn của nhà nước: [11 yêu cầu chung đối với cổng thông tin điện tử và trang thông tin điện tử của cơ quan nhà nước mới nhất là gì?](https://thuvienphapluat.vn/phap-luat/ho-tro-phap-luat/11-yeu-cau-chung-doi-voi-cong-thong-tin-dien-tu-va-trang-thong-tin-dien-tu-cua-co-quan-nha-nuoc-moi-662824-146628.html)

- Chức năng tối thiểu của cổng thông tin theo chuẩn: [Thông tư 22/2023/TT-BTTTT cấu trúc, bố cục cổng thông tin điện tử của cơ quan nhà nước mới nhất](https://thuvienphapluat.vn/van-ban/Cong-nghe-thong-tin/Thong-tu-22-2023-TT-BTTTT-cau-truc-bo-cuc-yeu-cau-ky-thuat-cho-cong-thong-tin-dien-tu-602088.aspx?anchor=chuong_pl_2)

- Trang web mẫu: [https://tuyensinh.ptit.edu.vn/](https://tuyensinh.ptit.edu.vn/)

## Key Features

- Xem các bài viết các loại: thông báo, tin tức, đề án tuyển sinh, xem điểm chuẩn các năm,...

- Đăng nhập quản trị để đăng bài, xác nhận/từ chối hồ sơ sinh viên...

- Đăng ký tk sinh viên & nộp hồ sơ

- (nếu có) Đổi tiếng EN - VIE

## Technologies Used
- Spring boot
## Getting Started
### Prerequisites (Yêu cầu Tiên quyết)
- Java Development Kit (JDK) >= 17

- IDE (IntelliJ IDEA)

- Git
### Installation
1. **Clone repository:**
   ```bash 
   git clone https://github.com/hoangtung2103/tuyensinh.git 
   ```

### Running the Application

2. Mở CSDL (MySQL) và chạy lần lượt các file truy vấn
   - `tuyensinh_db.sql` (bắt buộc p chạy truy vấn này)
   - `data_test.sql`
   - `feature_major_sample.sql` (bắt buộc p chạy truy vấn này)
   - `feature_xoabaiviet_sample.sql`

3. Trong dự án vừa mở ở IntelliJ, Mở folder `tuyensinh` và
   - tìm đến file: `tuyensinh/tuyensinh_be/src/main/resources/application.properties` thay đổi username password theo csdl của b

   - Sau đó, Tìm đến file có đường dẫn: `tuyensinh\tuyensinh_be\src\main\java\com\tungth\tuyensinh_be\TuyensinhBeApplication.java` và chạy nó.

All done!
## API Usage
Xem BE response shema tại: [BE response schema - Google Tài liệu](https://docs.google.com/document/d/1WPSlsxJ3mAnRJZy5hBciukRv-7-IR_JowY901DC9bgM/edit?tab=t.0)
## Contributing
Để đóng góp cho dự án này, bạn có thể làm theo các bước sau:
- **Fork the repository.**

- **Tạo một nhánh mới (branch).**

- **Thực hiện các thay đổi.**

- **Tạo Pull Request.**
## License

## Contact

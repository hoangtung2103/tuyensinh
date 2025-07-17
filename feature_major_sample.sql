
-- sửa lại cái cột major đã tạo đã
ALTER TABLE Majors
ADD article_id BIGINT,
ADD CONSTRAINT fk_major_article FOREIGN KEY (article_id) REFERENCES Articles(id);

-- sửa lại cột major code để tăng chiều dài mã ngành
ALTER TABLE Majors
MODIFY COLUMN majorCode VARCHAR(15);

-- Thêm dữ liệu mẫu
INSERT INTO Articles (id, title, coverImage, summary, content, category, subcategory, publishedDate, author) VALUES
(1, 'Giới thiệu ngành Công nghệ Thông tin', 'https://example.com/images/cntt.jpg', 'Tổng quan về ngành CNTT.', '<p>Chương trình đào tạo CNTT...</p>', 'tin-tuc', 'thong-tin-bao-chi', '2025-07-11', 'Nguyen Van A'),
(2, 'Giới thiệu ngành Trí tuệ nhân tạo', 'https://example.com/images/ktmt.jpg', 'Tổng quan về ngành Trí tuệ nhân tạo.', '<p>Chương trình đào tạo KTMT...</p>', 'tin-tuc', 'thong-tin-bao-chi', '2025-07-10', 'Nguyen Van B'),
(3, 'Giới thiệu ngành Trí tuệ nhân tạo vạn vật', 'https://example.com/images/ktmt.jpg', 'Tổng quan về ngành Trí tuệ nhân tạo.', '<p>Chương trình đào tạo KTMT...</p>', 'tin-tuc', 'thong-tin-bao-chi', '2025-07-10', 'Nguyen Van B');

INSERT INTO Majors (majorCode, majorName, quota, description, article_id) VALUES
('7480201_UDU', 'Công nghệ thông tin (cử nhân, định hướng ứng dụng)', 300, 'Chương trình đào tạo Công nghệ Thông tin chuẩn quốc tế.', 1),
('7480107', 'Ngành Trí tuệ nhân tạo', 150, 'Đào tạo kỹ sư máy tính với kỹ năng thực tiễn.', 2),
('7520207', 'Ngành Trí tuệ nhân tạo vạn vật (AIoT)', 150, 'Đào tạo kỹ sư máy tính với kỹ năng thực tiễn.', 3);



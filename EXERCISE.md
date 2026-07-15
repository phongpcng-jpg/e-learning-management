# **BÀI TẬP TỔNG HỢP: XÂY DỰNG REST API QUẢN LÝ KHÓA HỌC TRỰC TUYẾN (E-LEARNING)**

### 

### **Mục tiêu bài tập**

* Vận dụng linh hoạt các cơ chế nhận tham số HTTP Request thông qua các Annotation chủ đạo (@RequestBody, @ModelAttribute, @RequestParam, @PathVariable).  
* Triển khai kiến trúc bảo mật cấu trúc hệ thống thông qua tầng DTO (Data Transfer Object).  
* Áp dụng chuẩn Java Bean Validation (JSR-380) để kiểm tra tính hợp lệ dữ liệu đầu vào.  
* Thiết kế và đồng bộ cấu trúc phản hồi API trả về Client theo mẫu ApiResponse chuẩn hóa sử dụng Generic \<T\>.

### 

### **YÊU CẦU ĐỀ BÀI**

Đóng vai trò là một Backend Developer xây dựng cụm tính năng REST API cho mô-đun Quản lý Khóa học (Course) với các yêu cầu kỹ thuật chi tiết sau:

#### **Yêu cầu 1: Cấu trúc hóa dữ liệu (Tầng DTO)**

Hãy tự thiết kế một lớp CourseRequestDTO để nhận dữ liệu khi thêm mới hoặc cập nhật một khóa học. Lớp này phải cô lập với tầng cơ sở dữ liệu (Database) và chứa các trường dữ liệu cần thiết.

#### 

#### **Yêu cầu 2: Ràng buộc tính hợp lệ dữ liệu (Validation)**

Khi Client gửi dữ liệu lên thông qua API để thao tác với khóa học, hệ thống bắt buộc phải kiểm tra (validate) đầu vào trên lớp DTO theo đúng các luật sau:

1. **Mã khóa học (courseCode):** Không được để trống (cắt khoảng trắng), bắt buộc phải bắt đầu bằng chữ "KH-" và theo sau là 4 ký tự số (Ví dụ hợp lệ: KH-1234).  
2. **Tên khóa học (title):** Khác null, không rỗng, độ dài tối thiểu là 10 ký tự và tối đa là 150 ký tự.  
3. **Mô tả ngắn (description):** Được phép null nhưng nếu nhập thì độ dài sau khi cắt khoảng trắng phải lớn hơn 0  
4. **Học phí (price):** Bắt buộc phải là một số dương lớn hơn hoặc bằng 0\.  
5. **Thời lượng học (durationHours):** Bắt buộc phải là số nguyên dương lớn hơn 0\.  
6. **Email giảng viên phụ trách (instructorEmail):** Chuỗi phải tuân thủ đúng định dạng email tiêu chuẩn

#### 

#### **Yêu cầu 3: Xây dựng hệ thống REST Controller (Data Binding)**

Xây dựng lớp CourseController để xử lý các Endpoint sau (Yêu cầu kích hoạt đầy đủ cơ chế Validation đầu vào):

1. **POST /api/v1/courses (Tạo mới khóa học):** Ánh xạ trực tiếp dữ liệu dạng JSON từ HTTP Request Body vào đối tượng DTO.  
2. **PUT /api/v1/courses/{id} (Cập nhật khóa học):** Sử dụng cơ chế ánh xạ dữ liệu định danh nằm trực tiếp trên URI để xác định khóa học cần sửa, kết hợp ánh xạ dữ liệu JSON sửa đổi từ Request Body.  
3. **GET /api/v1/courses/search (Tìm kiếm nâng cao):** Cho phép Client truyền các điều kiện lọc (ví dụ: khoảng giá từ-đến, tên khóa học, trạng thái) bằng cách ánh xạ tự động từ Query Parameters (URL) hoặc Form Data vào một đối tượng tìm kiếm riêng.  
4. **GET /api/v1/courses (Phân trang danh sách):** Nhận các tham số đơn lẻ trên URL sau dấu ? để xử lý việc phân trang (ví dụ: số trang page, số lượng phần tử size).

#### 

#### **Yêu cầu 4: Chuẩn hóa dữ liệu trả về (ApiResponse Wrapper)**

Bất kể API thực hiện thành công hay gặp lỗi (lỗi nghiệp vụ hoặc lỗi dữ liệu không hợp lệ), cấu trúc dữ liệu trả về cho Frontend phải luôn cố định và bao gồm đúng 3 thành phần sau:

* **status**: Mã trạng thái phản hồi (Chuỗi hoặc mã số HTTP phù hợp).  
* **message**: Dòng thông báo rõ ràng, thân thiện với người dùng giải thích kết quả xử lý hoặc chi tiết lỗi đầu vào của trường dữ liệu nào bị vi phạm.  
* **data**: Kết quả thực tế trả về (là đối tượng dữ liệu hoặc danh sách). Nếu API thất bại hoặc bị lỗi Validation, phần này bắt buộc phải trả về null.

(Gợi ý: Sử dụng tính chất Generic \<T\> của Java để thiết kế lớp bọc phản hồi này nhằm tái sử dụng cho toàn bộ các Endpoint trên).


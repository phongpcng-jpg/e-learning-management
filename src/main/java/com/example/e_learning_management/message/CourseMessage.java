package com.example.e_learning_management.message;

public final class CourseMessage {

    public static final String COURSE_CODE_NOT_BLANK =
        "Mã khóa học không được để trống.";

    public static final String COURSE_CODE_INVALID = 
        "Mã khóa học không được đúng định dạng 'KH-' và theo sau là 4 ký tự số (Ví dụ hợp lệ: KH-1234).";

    public static final String TITLE_NOT_BLANK = 
        "Tiêu đề không được để trống.";

    public static final String TITLE_SIZE_INVALID = 
        "Tiêu đề phải có độ dài tối thiểu là 10 ký tự và tối đa là 150 ký tự.";

    public static final String DESCRIPTION_INVALID = 
        "Mô tả ngắn nếu nhập thì độ dài sau khi cắt khoảng trắng phải lớn hơn 0";

    public static final String PRICE_NOT_NULL = 
        "Giá của khóa học không được để trống.";

    public static final String PRICE_INVALID = 
        "Giá của khóa học phải là một số dương lớn hơn hoặc bằng 0.";

    public static final String DURATION_NOT_NULL = 
        "Thời lượng học không được để trống.";

    public static final String DURATION_INVALID = 
        "Thời lượng học phải là số nguyên dương lớn hơn 0.";

    public static final String INSTRUCTOR_EMAIL_NOT_BLANK = 
        "Email giảng viên phụ trách của khóa học không được để trống.";

    public static final String INSTRUCTOR_EMAIL_INVALID = 
        "Email giảng viên phụ trách của khóa học không được đúng định dạng email.";

}

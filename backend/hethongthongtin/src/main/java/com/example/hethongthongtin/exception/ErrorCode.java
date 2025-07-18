package com.example.hethongthongtin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(1001, "Không tìm thấy người dùng", HttpStatus.NOT_FOUND),
    PROFILE_NOT_FOUND(1002, "Không tìm thấy hồ sơ", HttpStatus.NOT_FOUND),
    INVALID_FILE(1003, "Tệp không hợp lệ", HttpStatus.BAD_REQUEST),
    REQUEST_NOT_FOUND(1004, "Không tìm thấy yêu cầu", HttpStatus.NOT_FOUND),
    INVALID_LOAI_VE(1005, "Loại vé không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_LOAI_YEU_CAU(1006, "Loại yêu cầu không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_LOAI_GIAY(1007, "Loại giấy tờ không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_TRANG_THAI(1008, "Trạng thái không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_REQUEST(1010, "Yêu cầu phân trang không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_SEARCH_PARAMS(1011, "Tham số tìm kiếm không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_ATTACHMENT_DATA(1012, "Dữ liệu tệp đính kèm không hợp lệ", HttpStatus.BAD_REQUEST),
    MSV_NOT_FOUND(1013, "Sinh viên chưa cập nhật đầy đủ hồ sơ", HttpStatus.INTERNAL_SERVER_ERROR),
    PHONG_NOT_FOUND(1014, "Không tìm thấy phòng", HttpStatus.NOT_FOUND),


    ;
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}

package com.AeonAssesment.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_ACTION_TYPE(501,"Invalid action type for the current controller."),
    INVALID_DATABASE_EXCEPTION(502, "Invalid database exception. "),
    SERVICE_EXCEPTION(503,"Service exception : "),
    INVALID_EMAIL_EXCEPTION(504, "Invalid Email format"),
    INVALID_BORROWING_ACTION(505, "Invalid ISBN as there is no book in records."),
    INVALID_NO_BOOK(506,"No more book is available to be borrow"),
    INVALID_EMAIL_EXIST(507, "Email already being registered."),
    INVALID_NOT_EXIST_USER(508, "Borrower not exist. "),
    INVALID_NOT_EXIST_BORROWING(509, "This borrowing transaction not exist"),
    INVALID_UPDATE(510, "Update failure. Please try again"),
    INVALID_MISSING_BOOKID(511,"Book ID is null.");

    private final int errorCode;
    private final String value;
    ErrorCode(final int errorCode, final String value )
    {
        this.errorCode = errorCode;
        this.value=value;
    }

    @Override
    public String toString(){
        return  errorCode + " - " + value;
    }
}

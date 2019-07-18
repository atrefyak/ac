package com.andriytrefyak.searchrouteservice.enumaration;

public enum ErrorCodeEnum {

    NOT_FOUND(404, "Requested data is not found"),
    BAD_REQUEST(400, "Please make sure that request is correct");

    private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong, please try again later";
    private int status;
    private String message;

    private ErrorCodeEnum(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public static String getErrorMessage(final int status) {
        for (ErrorCodeEnum errorCode : ErrorCodeEnum.values()) {
            if (errorCode.status == status) {
                return errorCode.message;
            }
        }
        return DEFAULT_ERROR_MESSAGE;
    }
}

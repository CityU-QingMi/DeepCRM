    public static Throwable getThrowable(ServletRequest request) {
        Throwable error = (Throwable) request.getAttribute(SERVLET_EXCEPTION);
        if (error == null) {
            error = (Throwable) request.getAttribute(JSP_EXCEPTION);
            if (error != null) {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
                request.setAttribute(SERVLET_EXCEPTION, error);
            }
        }

        return error;
    }

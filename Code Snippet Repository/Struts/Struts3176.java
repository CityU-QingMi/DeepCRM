        void setContentType() {
            HttpServletResponse response = ServletActionContext.getResponse();
            // set content type if it hasn't already been set.
            if (response.getContentType() == null || response.getContentType().isEmpty()) {
                response.setContentType(contentType);
            }
            // If no character encoding was set in the content type, default to UTF-8.
            if (response.getCharacterEncoding() == null || response.getCharacterEncoding().isEmpty()) {
                response.setCharacterEncoding("UTF-8");
            }
        }

    protected boolean preTemplateProcess(Template template, TemplateModel model) throws IOException {
        Object attrContentType = template.getCustomAttribute("content_type");

        HttpServletResponse response = ServletActionContext.getResponse();
        if (response.getContentType() == null) {
            if (attrContentType != null) {
                response.setContentType(attrContentType.toString());
            } else {
                String contentType = getContentType();

                if (contentType == null) {
                    contentType = "text/html";
                }

                String encoding = template.getEncoding();

                if (encoding != null) {
                    contentType = contentType + "; charset=" + encoding;
                }

                response.setContentType(contentType);
            }
        } else if(isInsideActionTag()){
             //trigger com.opensymphony.module.sitemesh.filter.PageResponseWrapper.deactivateSiteMesh()
            response.setContentType(response.getContentType());
        }

        return true;
    }

    private String generateJSON(HttpServletRequest request, HttpServletResponse response, ValidationAware validationAware)
            throws IOException {
        if (validationFailedStatus >= 0) {
            response.setStatus(validationFailedStatus);
        }
        setupEncoding(response, request);
        response.getWriter().print(buildResponse(validationAware));
        response.setContentType("application/json");
        return Action.NONE;
    }

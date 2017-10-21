    public void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {

        initializeProperties(invocation);
        
        if (!chartSet) // if our chart hasn't been set (by the testcase), we'll look it up in the value stack
            chart = (JFreeChart) invocation.getStack().findValue(value, JFreeChart.class);
        if (chart == null) // we need to have a chart object - if not, blow up
            throw new NullPointerException("No JFreeChart object found on the stack with name " + value);
        // make sure we have some value for the width and height
        if (height == null)
            throw new NullPointerException("No height parameter was given.");
        if (width == null)
            throw new NullPointerException("No width parameter was given.");

        // get a reference to the servlet output stream to write our chart image to
        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();
        try {
            // check the type to see what kind of output we have to produce
            if ("png".equalsIgnoreCase(type)) {
                response.setContentType("image/png");
                ChartUtilities.writeChartAsPNG(os, chart, getIntValueFromString(width), getIntValueFromString(height));
            }
            else if ("jpg".equalsIgnoreCase(type) || "jpeg".equalsIgnoreCase(type)) {
                response.setContentType("image/jpg");
                ChartUtilities.writeChartAsJPEG(os, chart, getIntValueFromString(width), getIntValueFromString(height));
            }
            else
                throw new IllegalArgumentException(type + " is not a supported render type (only JPG and PNG are).");
        } finally {
            if (os != null) os.flush();
        }
    }

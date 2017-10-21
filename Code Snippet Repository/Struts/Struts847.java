    public String include(Object aName) throws Exception {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(aName.toString());

            if (dispatcher == null) {
                throw new IllegalArgumentException("Cannot find included file " + aName);
            }

            ResponseWrapper responseWrapper = new ResponseWrapper(response);

            dispatcher.include(request, responseWrapper);

            return responseWrapper.getData();
        }
        catch (Exception e) {
            LOG.debug("Cannot include {}", aName.toString(), e);
            throw e;
        }
    }

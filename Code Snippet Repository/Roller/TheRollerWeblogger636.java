    private void renderException(Map<String, Object> model, Writer out,
            String template) throws RenderingException {

        try {

            if (template != null) {
                // need to lookup error page template
                velocityTemplate = RollerVelocity.getTemplate(template,
                        deviceType);
            }

            Context ctx = new VelocityContext(model);
            ctx.put("exception", velocityException);
            ctx.put("exceptionSource", renderTemplate.getId());
            ctx.put("exceptionDevice", deviceType);
            ctx.put("utils", new UtilitiesModel());

            // render output to Writer
            velocityTemplate.merge(ctx, out);

        } catch (Exception e) {
            // wrap and rethrow so caller can deal with it
            throw new RenderingException("Error during rendering", e);
        }

    }

    public int afterBody() throws TemplateModelException, IOException {
        afterBody = true;
        boolean result = bean.end(this, bean.usesBody() ? body.toString() : "");

        if (result) {
            return REPEAT_EVALUATION;
        } else {
            return END_EVALUATION;
        }
    }

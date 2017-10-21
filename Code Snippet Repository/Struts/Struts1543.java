    public void testExpressionParams() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionSupport action = new ActionSupport() {

            public Double getMinInclusiveValue() {
                return 10d;
            }

            public Double getMaxInclusiveValue() {
                return 11d;
            }

            public Double getMinExclusiveValue() {
                return 13d;
            }

            public Double getMaxExclusiveValue() {
                return 14d;
            }

            public Double getPrice() {
                return 15d;
            }
        };

        stack.push(action);

        val.setMinInclusiveExpression("${minInclusiveValue}");
        val.setMaxInclusiveExpression("${maxInclusiveValue}");
        val.setMinExclusiveExpression("${minExclusiveValue}");
        val.setMaxExclusiveExpression("${maxExclusiveValue}");

        val.setFieldName("price");
        val.setDefaultMessage("Price is wrong!");

        DelegatingValidatorContext context = new DelegatingValidatorContext(action, tpf);
        val.setValidatorContext(context);

        val.validate(action);
        assertTrue(action.getFieldErrors().get("price").size() == 1);
    }

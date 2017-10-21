    public void setFullOrder() {

        isFullOrder = true;

        if (leftQueryExpression != null) {
            leftQueryExpression.setFullOrder();
        }

        if (rightQueryExpression != null) {
            rightQueryExpression.setFullOrder();
        }
    }

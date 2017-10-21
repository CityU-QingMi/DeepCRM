    public Expression duplicate() {

        ExpressionLike e = (ExpressionLike) super.duplicate();

        if (likeObject != null) {
            e.likeObject = likeObject.duplicate();
        }

        return e;
    }

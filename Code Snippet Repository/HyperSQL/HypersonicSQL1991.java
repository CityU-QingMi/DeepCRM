    private void resize(int baseSize) {

        if (baseSize == elementData.length) {
            return;
        }

        Object[] newArray = (Object[]) Array.newInstance(
            elementData.getClass().getComponentType(), baseSize);
        int count = elementData.length > newArray.length ? newArray.length
                                                         : elementData.length;

        System.arraycopy(elementData, 0, newArray, 0, count);

        if (minimizeOnClear && reserveElementData == null) {
            ArrayUtil.clearArray(ArrayUtil.CLASS_CODE_OBJECT, elementData, 0,
                                 elementData.length);

            reserveElementData = elementData;
        }

        elementData = newArray;
    }

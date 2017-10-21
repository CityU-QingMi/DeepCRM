    @Override
    public Fruit decode(String s) throws DecodeException
    {
        Pattern pat = Pattern.compile("([^|]*)|([^|]*)");
        Matcher mat = pat.matcher(s);
        if (!mat.find())
        {
            throw new DecodeException(s,"Unable to find Fruit reference encoded in text message");
        }

        Fruit fruit = new Fruit();
        fruit.name = mat.group(1);
        fruit.color = mat.group(2);

        return fruit;
    }

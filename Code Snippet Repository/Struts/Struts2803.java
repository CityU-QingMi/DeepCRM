    String getText(Mark start, Mark stop) throws JasperException {
        Mark oldstart = mark();
        reset(start);
        CharArrayWriter caw = new CharArrayWriter();
        while (!stop.equals(mark()))
            caw.write(nextChar());
        caw.close();
        reset(oldstart);
        return caw.toString();
    }

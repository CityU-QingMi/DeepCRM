    public void printComment(Mark start, Mark stop, char[] chars) {
        if (start != null && stop != null) {
            println("// from=" + start);
            println("//   to=" + stop);
        }

        if (chars != null)
            for (int i = 0; i < chars.length; ) {
                printin();
                print("// ");
                while (chars[i] != '\n' && i < chars.length)
                    writer.print(chars[i++]);
            }
    }

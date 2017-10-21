    @Override
    public void render(final StringBuilder input, final StringBuilder output) throws IllegalArgumentException {
        int i = 0;
        int j, k;

        while (true) {
            j = input.indexOf(beginToken, i);
            if (j == -1) {
                if (i == 0) {
                    output.append(input);
                    return;
                }
                output.append(input.substring(i, input.length()));
                return;
            }
            output.append(input.substring(i, j));
            k = input.indexOf(endToken, j);

            if (k == -1) {
                output.append(input);
                return;
            }
            j += beginTokenLen;
            final String spec = input.substring(j, k);

            final String[] items = spec.split(AnsiRenderer.CODE_TEXT_SEPARATOR, 2);
            if (items.length == 1) {
                output.append(input);
                return;
            }
            final String replacement = render(items[1], items[0].split(","));

            output.append(replacement);

            i = k + endTokenLen;
        }
    }

    private int nameHashCode()
    {
        int h = this.hash;
        int len = _name.length();
        if (h == 0 && len > 0)
        {
            for (int i = 0; i < len; i++)
            {
                // simple case insensitive hash
                char c = _name.charAt(i);
                // assuming us-ascii (per last paragraph on http://tools.ietf.org/html/rfc7230#section-3.2.4)
                if ((c >= 'a' && c <= 'z'))
                    c -= 0x20;
                h = 31 * h + c;
            }
            this.hash = h;
        }
        return h;
    }

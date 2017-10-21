        public String toString() {

            StringBuffer sb = new StringBuffer("Addrs ");

            sb.append((value.length == 16)
                      ? ("[" + ServerAcl.colonNotation(value) + ']')
                      : ServerAcl.dottedNotation(value));
            sb.append("/" + bitBlockSize + ' ' + (allow ? "ALLOW"
                                                        : "DENY"));

            return sb.toString();
        }

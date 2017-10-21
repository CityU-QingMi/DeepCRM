    public static boolean equalsIgnoreEncodings(String uriA, String uriB)
    {
        int lenA=uriA.length();
        int lenB=uriB.length();
        int a=0;
        int b=0;
        
        while (a<lenA && b<lenB)
        {
            int oa=uriA.charAt(a++);
            int ca=oa;
            if (ca=='%')
                ca=TypeUtil.convertHexDigit(uriA.charAt(a++))*16+TypeUtil.convertHexDigit(uriA.charAt(a++));
            
            int ob=uriB.charAt(b++);
            int cb=ob;
            if (cb=='%')
                cb=TypeUtil.convertHexDigit(uriB.charAt(b++))*16+TypeUtil.convertHexDigit(uriB.charAt(b++));
            
            if (ca=='/' && oa!=ob)
                return false;
            
            if (ca!=cb )
                return URIUtil.decodePath(uriA).equals(URIUtil.decodePath(uriB));
        }
        return a==lenA && b==lenB;
    }

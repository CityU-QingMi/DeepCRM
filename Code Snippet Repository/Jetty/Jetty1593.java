    @Parameters
    public static Collection<String[]> data()
    {
        List<String[]> data = new ArrayList<>();
        bad(data,"/a/b{var}"); // bad syntax - variable does not encompass whole path segment
        bad(data,"a/{var}"); // bad syntax - no start slash
        bad(data,"/a/{var/b}"); // path segment separator in variable name
        bad(data,"/{var}/*"); // bad syntax - no globs allowed
        bad(data,"/{var}.do"); // bad syntax - variable does not encompass whole path segment
        bad(data,"/a/{var*}"); // use of glob character not allowed in variable name
        bad(data,"/a/{}"); // bad syntax - no variable name
        // MIGHT BE ALLOWED bad(data,"/a/{---}"); // no alpha in variable name
        bad(data,"{var}"); // bad syntax - no start slash
        bad(data,"/a/{my special variable}"); // bad syntax - space in variable name
        bad(data,"/a/{var}/{var}"); // variable name duplicate
        // MIGHT BE ALLOWED bad(data,"/a/{var}/{Var}/{vAR}"); // variable name duplicated (diff case)
        bad(data,"/a/../../../{var}"); // path navigation not allowed
        bad(data,"/a/./{var}"); // path navigation not allowed
        bad(data,"/a//{var}"); // bad syntax - double path slash (no path segment)
        return data;
    }

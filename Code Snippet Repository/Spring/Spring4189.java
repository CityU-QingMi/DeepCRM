    static Handler remove(Handler h, Label start, Label end) {
        if (h == null) {
            return null;
        } else {
            h.next = remove(h.next, start, end);
        }
        int hstart = h.start.position;
        int hend = h.end.position;
        int s = start.position;
        int e = end == null ? Integer.MAX_VALUE : end.position;
        // if [hstart,hend[ and [s,e[ intervals intersect...
        if (s < hend && e > hstart) {
            if (s <= hstart) {
                if (e >= hend) {
                    // [hstart,hend[ fully included in [s,e[, h removed
                    h = h.next;
                } else {
                    // [hstart,hend[ minus [s,e[ = [e,hend[
                    h.start = end;
                }
            } else if (e >= hend) {
                // [hstart,hend[ minus [s,e[ = [hstart,s[
                h.end = start;
            } else {
                // [hstart,hend[ minus [s,e[ = [hstart,s[ + [e,hend[
                Handler g = new Handler();
                g.start = end;
                g.end = h.end;
                g.handler = h.handler;
                g.desc = h.desc;
                g.type = h.type;
                g.next = h.next;
                h.end = start;
                h.next = g;
            }
        }
        return h;
    }

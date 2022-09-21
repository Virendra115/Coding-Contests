static class Trie {
        Trie[] children;
        boolean end;
        long val;
        Trie(){
            val = 0;
            end = false;
            children = new Trie[26];
        }
    }
    static void insert(String s,Trie root,long val) {
        Trie tmp = root;
        for (int i = 0; i < s.length(); i++) {
            int j = s.charAt(i) - 'a';
            if (tmp.children[j] == null)
                tmp.children[j] = new Trie();
            tmp = tmp.children[j];
            tmp.val = (tmp.val + val)%1_000_000_007;
        }
        tmp.end = true;
    }
    static long count(String s,Trie root) {
        Trie tmp = root;
        for (int i = 0; i < s.length(); i++) {
            int j = s.charAt(i) - 'a';
            if (tmp.children[j] == null)
                return 0;
            tmp = tmp.children[j];
        }
        return tmp.val;
    }

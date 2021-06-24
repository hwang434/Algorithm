import java.util.*;

class Solution {
    public List<String> commonChars(String[] words) {
        //기준이 될 map.
        Map<String, Integer> map = new HashMap<>();
        //각 word를 돌 때마다 알파벳의 개수를 세서 map과 비교할 copyMap.
        Map<String, Integer> copyMap = new HashMap<>();

        //제일 첫번째 words[0]으로 기준이 될 map을 만들어 줍니다.
        for (char w : words[0].toCharArray()) {
            String s = String.valueOf(w);
            map.put(s, map.getOrDefault(s,0)+1);

            //copyMap에는 기준이 될 Map의 키만 등록합니다.
            //개수는 밑에 for문에서 넣어줄겁니다.
            copyMap.put(s,0);
        }

        for (int i = 1; i < words.length; i++) {
            for (int j = 0;  j < words[i].length(); j++) {
                String s = String.valueOf(words[i].charAt(j));

                //null일 경우, 기준에 없는 문자로 뒤에서 몇번이 나오든 처음에 안나왔기 때문에 확인할 필요가 없습니다.
                //null이 아닐 경우 해당 문자가 몇번 나왔는지 세줍니다.
                if (copyMap.get(s) != null) {
                    copyMap.put(s,copyMap.get(s)+1);
                }
            }

            Iterator<String> it = copyMap.keySet().iterator();
            while (it.hasNext()){
                String s = it.next();

                //기준이 되는 알파벳의 개수와 비교 되는 알파벳 중에 더 작은 값이
                //최대 나올 수 있는 개수입니다.
                //ex : eee, ee, 공통이 될 수 있는 개수는 2개.
                int num = Math.min(map.get(s),copyMap.get(s));
                map.put(s,num);
            }

            //다시 다음 문자열로 넘어가서 처음부터 문자를 세야하므로,
            //모든 key에 0을 넣어서 개수를 초기화해줍니다.
            copyMap.forEach((key, value)  -> copyMap.put(key,0));
        }

        List<String> list = new ArrayList<>();
        for (String s : map.keySet()) {
            int loopCount = map.get(s);
            while (loopCount-- > 0) {
                list.add(s);
            }
        }
        return list;
    }
}

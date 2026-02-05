package hackerton.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/*
    조건을 만족하는 에이전트 ID를
    정렬된 순서대로 String 배열로 반환하라.
필터링
    A1 (IDLE, 12)

    A3 (IDLE, 7)
정렬
    A3 (7)
    A1 (12)
*/
public class SortfilteringParsing {
    public static void main(String[] args) {
        //String input = "[{\"agentId\":\"A1\",\"status\":\"DELIVERING\",\"distance\":12}, {\"agentId\":\"A2\",\"status\":\"IDLE\",\"distance\":3}]";
        //Objectmapper때문에 아래처럼 줌
        Solution solution = new Solution();
        String[] agentId = {"A1", "A2", "A3"};
        String[]status  = {"IDLE", "DELIVERING", "IDLE"};
        int[] distance= {12, 5, 7};
        solution.solutionArrays(agentId,status,distance);
    }
}
class Agent {
    public String agentId;
    public String status;
    public int distance;

    public Agent(String agentId, String status, int distance) {
        this.agentId = agentId;
        this.status = status;
        this.distance = distance;
    }
}

class Solution {
    public void solutionArrays(String[] agentId, String[] status, int[] distance) {
        Arrays.stream(agentId).sequential();
        List<Agent> agentList = new ArrayList<>();
        for(int i=0; i< agentId.length; i++) {
            Agent agent = new Agent(agentId[i], status[i], distance[i]);
            agentList.add(agent);
        }
        //stream 필터링
        List<Agent> filteredAgentList =
        agentList.stream().filter(a-> "IDLE".equals(a.status)).collect(Collectors.toList());
        // 3) 정렬 (distance → agentId)
        filteredAgentList.sort(Comparator.comparing((Agent a)-> a.distance).thenComparing(b -> b.agentId));

        //한방에
        List<Agent> filteredSorted =
                agentList.stream()
                        .filter(a -> "IDLE".equals(a.status))
                        .sorted(
                                Comparator.comparingInt((Agent a) -> a.distance)
                                        .thenComparing(a -> a.agentId)
                        )
                        .collect(Collectors.toList());   // mutable!

        //2) IDLE 필터링 for문 예시
        List<Agent> filtered = new ArrayList<>();
        for (Agent a : filteredAgentList) {
            if ("IDLE".equals(a.status)) {
                filtered.add(a);
            }
        }
        String[] results = new String[filteredAgentList.size()];

        for(int i = 0; i< filteredAgentList.size(); i++) {
            results[i] = filteredAgentList.get(i).agentId;
        }

        //출력
        for(String result : results) {
            System.out.println(result);
        }
    }
}

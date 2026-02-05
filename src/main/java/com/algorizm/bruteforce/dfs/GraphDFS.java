package com.algorizm.bruteforce.dfs;

import java.util.*;

public class GraphDFS {
/*
문제: 차량 센서 네트워크의 그룹(Connected Components) 개수 찾기
 차량 센서는 서로 무선 통신이 가능한 노드들로 구성됩니다.
센서 하나가 노드(Node)
서로 통신 가능하면 엣지(Edge)
다음 정보가 주어질 때,
전체 센서(노드)들이 몇 개의 그룹(connected components) 으로 나뉘는지 구하세요.
입력 설명
N = 센서 개수 (노드 수)
센서 번호는 0 ~ N-1
connections[i] = [a, b]
→ a번 센서와 b번 센서가 통신 가능 (양방향 그래프)
출력
연결된 센서 그룹의 개수
* */
    public static void main(String[] args) {

        int N = 6;
        int[][] connections = {
                {0, 1},
                {1, 2},
                {3, 4}
        };

        GraphDFS solver = new GraphDFS();
        int result = solver.solution(N, connections);

        System.out.println("그룹 개수 = " + result);  // 기대값: 3
    }

    public int solution(int N, int[][] connections) {

        // TODO: 여기에서 DFS 로직 구현해줘!
        // 1. 그래프 만들기
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <N; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : connections) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // 2. visited 배열 만들기
        boolean[] visited = new boolean[N];

        // 3. 0~N-1 모든 노드 돌면서 방문 안 했으면 DFS + count++
        int count =0;
        for(int i=0; i<N; i++) {//모든 노드를 돈다
            if(!visited[i]){
                dfs(i, visited, graph);
                count ++;
            }
        }
        // 4. count 리턴

        return count; // 임시 리턴
    }

    private void dfs(int node, boolean[] visited, List<List<Integer>> graph) {
        // TODO: DFS 구현
        // 현재 노드 방문 처리
        visited[node] = true;
        for(int next : graph.get(node)) {
            if(!visited[next]) {
                dfs(next, visited, graph);
            }
        }
        // for(다음 노드들) {
        //    if 방문 안 된 곳이면 dfs(next)
        // }

    }
}

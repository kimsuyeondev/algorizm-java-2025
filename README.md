알고리즘 핵심 정리

1. BFS (너비 우선 탐색)

언제? → 최단 거리 (비용이 모두 1일 때) 
자료구조 → Queue  
핵심 → 가까운 것부터 차례로 탐색  
- 비용이 다르면 → 다익스트라로 전환  

  ---
2. DFS (깊이 우선 탐색)

언제? → 영역 개수, 연결 확인, 사이클 검사  
자료구조 → 재귀 or Stack   

핵심 → 끝까지 갔다가 막히면 되돌아옴  
기본 패턴:  
```java
void dfs(int node) {
visited[node] = true;
for (int next : graph[node]) {
if (!visited[next]) dfs(next);
}
}  
```

  ---
3. Greedy (탐욕)

언제? → "지금 최선"이 "전체 최선"일 때  
핵심 → 정렬 + 앞에서부터 선택 or PriorityQueue  
패턴:  
```java
jobs.sort((a,b) -> a.end - b.end);  // 정렬 후  
for (Job j : jobs) {
if (j.start >= lastEnd) { count++; lastEnd = j.end; }
}
```
  ---
4. 가중치 BFS = 다익스트라

언제? → 간선 비용이 다를 때 최단 경로  
자료구조 → PriorityQueue  
핵심 → 비용이 작은 노드부터 꺼내서 갱신  

| BFS | 다익스트라 |
|-----|------------|
| Queue | PriorityQueue |
| 거리 +1 | 비용이 다름 |

  ---
5. 완전탐색 (Brute Force)

언제? → 경우의 수가 작을 때 (N ≤ 20 정도)  
핵심 → 모든 경우를 다 해보고 답 고르기  

3가지 패턴

| 패턴 | 복잡도 | 용도 |
|------|--------|------|
| for 중첩 | O(n²) | 작은 케이스 |
| DFS 재귀 | O(n!) | 순열/조합 |
| 비트마스크 | O(2^n) | 부분집합 |

백트래킹 패턴:
```java
void dfs(int depth) {
if (depth == target) { return; }
for (int i = 0; i < N; i++) {
if (!visited[i]) {
visited[i] = true;
dfs(depth + 1);
visited[i] = false;  // 복원!
}
}
}
```
  ---
6. DP (동적 계획법)

핵심 → 이전 결과를 재사용해서 현재 최적값 계산  
공식 → "i에 오기 직전 위치"를 나열하면 자연스럽게 나옴  

3가지 유형:

| 유형 | 패턴 | 예시 |
|------|------|------|
| 연속 부분 최적 | `dp[i] = max(dp[i-1]+arr[i], arr[i])` | 연속 최대합 |
| 누적 최적 (1D) | `dp[i] = min(dp[i-1], dp[i-2]) + cost[i]` | 계단 오르기 |
| 경로 누적 (2D) | `dp[i][j] = A[i][j] + min(dp[i-1][j], dp[i][j-1])` | 격자 최소비용 |

격자 문제 판단:  
- 이동이 오른쪽/아래만 → DP 가능  
- 이동이 상하좌우 자유 → 다익스트라만 가능  

  ---
7. 정렬 (퀵소트)

핵심 → pivot 기준으로 작은것/큰것 분리 후 재귀  
평균: O(n log n), 최악: O(n²)  
Lomuto Partition:  

i = low  
j를 low~high-1 순회  
a[j] ≤ pivot → swap(a[i], a[j]), i++  
마지막에 swap(a[i], a[high])

package com.company.Programmers;

import java.util.PriorityQueue;

/***
 * 95 59 중에 더 큰 수로 우선순위를 결정함
 * 303 330 도 마찬가지로
 * 작은 값이 가장 먼저 나오게 함 minHeap을 이용한 것
 * 이런게 우선순위 큐의 방식이다. 낮은값일수록 우선순위가 높으니깐
 *
 */
//class Node implements  Comparable<Node>{
//    String number;
//    public Node(String number){
//        this.number = number;
//    }
//
//    @Override
//    public int compareTo(Node o) {
//        return 0;
//    }
//}
public class MaxNumber {
    public String solution(int[] numbers) {
        //PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)

        //PriorityQueue는 우선순위가 가장 높은 데이터부터 먼저 나오게하는 큐이다.
        // 값들이 String으로 들어오는데 그 우선순위를 결정해주면 된다.
        //원래는 사전순서대로 String을 비교하는데 무시하고 새로구현한 방식을 사용.
        PriorityQueue<String> pq = new PriorityQueue<>((o1,o2)->{
           if(o1.equals(o2)){
               return 0;
           }else{
               if (Integer.parseInt(o1+o2) < Integer.parseInt(o2+o1)){
                   return 1;
                   // o1이 더 작은거면 뒤로가야하니깐(큰 숫자가 우선순위가 높다- 여기서는 앞의 글자를 비교하기 위해서 앞에 갈자가 크면 큰 수이다.)
                   // 작은 수가 우선순위가 높게 할려면 o1+o2 > o2+o1 이렇게 부호만 바꿔주면 된다.
                   // o1: 6,  o2 :10 -> 610 < 106 비교했을 때 false이므로 return -1을 반환
                   // o1: 10,o2 :2 - > 102 < 210 비교하면 true이니깐 1을 반환한다.
                   // return 값이 1 이 되면 o1의 값은 우선 순위가 밀려 뒤로 간다. (뒤로 가는게 1이다.)
                   // 오름차순으로 정렬이 되니깐
               }else{
                   return -1;
               }

           }
        });//순서대로 뽑을 수 있다. 오버라이딩한경우만 넣음 함수형프로그래밍
        for (int number : numbers){
            pq.offer(String.valueOf(number)); //offer로 넣을 수 있다.
        }

        assert pq.peek() != null;//peek가 null이 아닌지 확인하는 것, null이라면 프로그램이 멈추게 된다.
        //assert문을 사용한 Assertion(검증)은 주로 프로그램 흐름에서 코드가 정상적으로 처리되기 위해서
        // 필요한 조건을 지정할 수 있게 해주고, 이를 위반할 시에 AssertionError를 발생시켜주는 기능이다
        // 앞에 오는게 0이라면 (우선순위가 가장 높은게 0, 여기서는 가장 큰값을 의미하니깐 0이 가장 큰 수라고하면 나머지값들도 0이라는 의미, 0보다 작다는 의미이다.)
        if (pq.peek().equals("0")){
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        while (!pq.isEmpty()){
            builder.append(pq.poll()); // 우선 순위가 높은 숫자를 제거하면서 builder에 추가한다.
        }


        return builder.toString();
    }

    public static void main(String[] args) {
        MaxNumber maxNumber = new MaxNumber();
        int[] numOne = {6, 10, 2};
        int[] numTwo = {3,30,34,5,9};
        System.out.println(maxNumber.solution(numOne));
        System.out.println(maxNumber.solution(numTwo));
    }
}

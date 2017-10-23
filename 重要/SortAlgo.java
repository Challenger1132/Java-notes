package sort;

import java.util.Arrays;
import java.util.Random;

public class SortAlgo {
	/* ð�������㷨 ��������Ԫ�أ����Ԫ���³�����һ������Ԫ����������
	 * �ȶ�
	 * ʱ�临�Ӷ� O(n^2)  �ռ临�Ӷ� O(1)
	 * */
	public static void bubbleSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-i-1; j++) {
				if(arr[j] > arr[j+1])
					swap(arr, j, j+1);
			}
		}
	}
	
	
	/* ѡ�����򣬵�ǰԪ�غ͸�Ԫ��֮���Ԫ�ؽ��бȽϣ��ҵ�����Ԫ�ص���Сֵ�������Сֵ�����ڵ�ǰԪ�أ��͵�ǰԪ�ؽ��н��������򣬲�����
	 * ���ȶ�
	 * ʱ�临�Ӷ� O(n^2)  �ռ临�Ӷ� O(1)
	 * */
	public static void selectSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			int minIndex = getMin(arr, i);  //�õ���СԪ�ض�Ӧ��index
			if(minIndex != i)  //�����СԪ�ز����ڵ�ǰԪ�أ��ͽ���
				swap(arr, minIndex, i);
		}
	}
	private static int getMin(int[] arr, int index){
		int minIndex = index;
		for (int i = index+1; i < arr.length; i++) {
			if(arr[minIndex] > arr[i])
				minIndex = i;
		}
		return minIndex;
	}
	
	
	/* ��������    ���ڲ�����������У�������
	 * �ȶ�
	 * ʱ�临�Ӷ� O(n^2)  �ռ临�Ӷ� O(1)
	 * */
	public static void insertSort(int[] arr){
		for (int i = 1; i < arr.length; i++) {
			int j = i, ele = arr[i];
			for ( ; j > 0 && arr[j-1] > ele; j--)  // or j >= 1
				arr[j] = arr[j-1];
			arr[j] = ele;
		}
	}


	/* ϣ������ֻ��Ҫ�������������������forѭ������һ����������ֻ�轫���е�1��Ϊstep����
	 * ���ȶ�
	 * ʱ�临�Ӷ� O(nlogn) -- O(n^2) ���ܺ���������Ķ����й�    �ռ临�Ӷ� O(1)
	 * */
	public static void shellSort(int[] arr){
		for (int step = arr.length / 2; step > 0; step /= 2) {  //�����������
			for (int i = step; i < arr.length; i++) {
				int j = i, ele = arr[i];
				for ( ; j >= step && arr[j-step] > ele; j -= step)  // or j > step - 1
					arr[j] = arr[j-step];
				arr[j] = ele;
			}
		}
	}
	
	
	/* �������򣬻�׼Ԫ��ѡ���м�λ��
	 * �����Ƿ���˼�룬��"��"����С�ڻ�׼��Ԫ�طŵ���׼��ߣ����ڻ�׼��Ԫ�ص�����׼���ұߣ�
	 * ��"��"���ֱ�������������ٴν�����������
	 * 
	 * �����ڴ���������ݵ�����
	 * ע���׼��ѡ����������ܵ�Ӱ�죬 ע������������ظ�Ԫ�صĴ���
	 * 
	 * ����������
	 * 	�����в��ÿ��ţ���������зָ�ɵ�С���в��ò�������
	 * 	������һС���ֵ���λ����Ϊ��׼Ԫ��(����ȡ�������ȡ�����߷�ȡ����)
	 * 	���������
	 * 
	 * ���ȶ�
	 * ʱ�临�Ӷ� O(nlogn)   �ռ临�Ӷ�  O(logn) -- O(n)���ݹ����ջ
	 * */
	public static void quickSort(int[] arr){  // �������
		int left = 0, right = arr.length-1;
		myquickSort(arr, left, right);
	}
	private static void myquickSort(int[] arr, int left, int right){
		if(left >= right)
			return;
		int i = left, j = right, p = left + (right - left)/2, pivot = arr[p];
		while(i != j){
			while(j > p && arr[j] >= pivot)
				j--;
			if(j > p){
				arr[p] = arr[j];
				p = j;
			}
			while(i < p && arr[i] <= pivot)
				i++;
			if(i < p){
				arr[p] = arr[i];
				p = i;
			}
		}
		arr[i] = pivot;
		myquickSort(arr, left, i-1);
		myquickSort(arr, i+1, right);
	}
	
	
	/* �������򣬻�׼Ԫ��ѡ�������
	 * */
	public static void quickSort2(int[] arr){  // �������
		int left = 0, right = arr.length-1;
		myquickSort2(arr, left, right);
	}
	private static void myquickSort2(int[] arr, int left, int right){
		if(left >= right)
			return;
		int index = partion(arr, left, right);
		myquickSort2(arr, left, index-1);
		myquickSort2(arr, index+1, right);
	}
	private static int partion(int[] arr, int left, int right){
		int i = left, j = right, pivot = arr[i];
		while(i != j){
			while(i < j && arr[j] > pivot)
				j--;
			if(i < j)
				arr[i++] = arr[j];
			while(i < j && arr[i] < pivot)
				i++;
			if(i < j)
				arr[j--] = arr[i];
		}
		arr[i] = pivot;
		return i;
	}
	
	
	
	/* �鲢����  nlogn ��Ҫ����ĸ����ռ�
	 * ����˼�룬��һ�����������Ƚ����зֳ������֣������ֱַ���������������������ֽ��й鲢����
	 * �ȶ�
	 * 
	 * ʱ�临�Ӷ� O(nlogn)  �ռ临�Ӷ�  O(n)
	 * */
	private static void mergeSort(int[] arr){  // �������
		int left = 0, right = arr.length-1;
		int[] helperArray = new int[arr.length];
		mymergeSort(arr, helperArray, left, right);
	}
	private static void mymergeSort(int[] arr, int[] helperArray, int left, int right){
		if(left >= right)
			return;
		int mid = left + (right - left) / 2;
		mymergeSort(arr, helperArray, left, mid);  //����߲��ֽ�������
		mymergeSort(arr, helperArray, mid+1, right); // ���ұ߲��ֽ�������
		merge(arr, helperArray, left, mid, right); // �����������ֽ��й鲢����
	}
	/* ʵ������������Ԫ�صĹ鲢����
	 * */
	private static void merge(int[] arr, int[] helperArray, int left, int mid, int right){
		//��Ԫ�طֳ������֣���벿�� left -- mid�� �Ұ벿�� mid+1 -- right
		//iָ����벿�ֿ�ʼλ�ã�jָ���Ұ벿�ֿ�ʼλ��
		//  ��벿�� [left, ..., mid] 		�Ұ벿��[mid+1, ... , right]
		int i = left, j = mid+1;
		//��arr�е�Ԫ�ؿ�����helperArray��
		System.arraycopy(arr, left, helperArray, left, right-left+1);
		for(int k = left; k <= right; k++){
			if(i > mid)  //��벿�����꣬�����Ұ벿��
				arr[k] = helperArray[j++];
			else if(j > right) //�Ұ벿�����꣬������벿��
				arr[k] = helperArray[i++];
			else if(helperArray[i] < helperArray[j])  //��벿��ĳ��Ԫ�� ���Ұ벿��ĳ��Ԫ�رȽϣ�����Ԫ�ؽ�ֵС��
				arr[k] = helperArray[i++];
			else if(helperArray[i] >= helperArray[j])
				arr[k] = helperArray[j++];
		}
	}
	
	/* ������ ...
	 * ���ȶ�
	 * */
	
	/* Ͱ���� ...
	 * �����������Ը��Ӷ�
	 * */
	
	/* ʵ��Ԫ�صĽ���
	 * */
	private static void swap(int[] arr, int l, int r){
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}
	
	/* ������������
	 * */
	public static int[] generateNumArray(int cnt, int limit){
		Random r = new Random();
		int[] arr = new int[cnt];
		for (int i = 0; i < cnt; i++)
			arr[i] = r.nextInt(limit);
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = generateNumArray(15, 100);
		mergeSort(arr);  //ѡ��������㷨
		System.out.println(Arrays.toString(arr));
	}
}

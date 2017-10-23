package sort;

import java.util.Arrays;
import java.util.Random;

public class SortAlgo {
	/* 冒泡排序算法 交换相邻元素，打的元素下沉，第一遍最大的元素在最下面
	 * 稳定
	 * 时间复杂度 O(n^2)  空间复杂度 O(1)
	 * */
	public static void bubbleSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-i-1; j++) {
				if(arr[j] > arr[j+1])
					swap(arr, j, j+1);
			}
		}
	}
	
	
	/* 选择排序，当前元素和该元素之后的元素进行比较，找到其中元素的最小值，如果最小值不等于当前元素，和当前元素进行交换，否则，不交换
	 * 不稳定
	 * 时间复杂度 O(n^2)  空间复杂度 O(1)
	 * */
	public static void selectSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			int minIndex = getMin(arr, i);  //得到最小元素对应的index
			if(minIndex != i)  //如果最小元素不等于当前元素，就交换
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
	
	
	/* 插入排序    对于部分有序的序列，是最快的
	 * 稳定
	 * 时间复杂度 O(n^2)  空间复杂度 O(1)
	 * */
	public static void insertSort(int[] arr){
		for (int i = 1; i < arr.length; i++) {
			int j = i, ele = arr[i];
			for ( ; j > 0 && arr[j-1] > ele; j--)  // or j >= 1
				arr[j] = arr[j-1];
			arr[j] = ele;
		}
	}


	/* 希尔排序，只需要定义好增量间隔，最外层for循环就是一个插入排序，只需将其中的1变为step就行
	 * 不稳定
	 * 时间复杂度 O(nlogn) -- O(n^2) 性能和增量间隔的定义有关    空间复杂度 O(1)
	 * */
	public static void shellSort(int[] arr){
		for (int step = arr.length / 2; step > 0; step /= 2) {  //定义增量间隔
			for (int i = step; i < arr.length; i++) {
				int j = i, ele = arr[i];
				for ( ; j >= step && arr[j-step] > ele; j -= step)  // or j > step - 1
					arr[j] = arr[j-step];
				arr[j] = ele;
			}
		}
	}
	
	
	/* 快速排序，基准元素选在中间位置
	 * 快排是分治思想，先"分"，将小于基准的元素放到基准左边，大于基准的元素当道基准的右边，
	 * 再"治"，分别对左右两部分再次进行上述过程
	 * 
	 * 适用于大量随机数据的排序
	 * 注意基准的选择对排序性能的影响， 注意对于序列中重复元素的处理
	 * 
	 * 如何提高性能
	 * 	大序列采用快排，排序过程中分割成的小序列采用插入排序
	 * 	将序列一小部分的中位数作为基准元素(三分取样，五分取样，七分取样等)
	 * 	序列随机化
	 * 
	 * 不稳定
	 * 时间复杂度 O(nlogn)   空间复杂度  O(logn) -- O(n)，递归调用栈
	 * */
	public static void quickSort(int[] arr){  // 方便调用
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
	
	
	/* 快速排序，基准元素选在最左边
	 * */
	public static void quickSort2(int[] arr){  // 方便调用
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
	
	
	
	/* 归并排序，  nlogn 需要额外的辅助空间
	 * 分治思想，对一个序列排序，先将序列分成两部分，两部分分别进行排序，最后将排序的两部分进行归并处理
	 * 稳定
	 * 
	 * 时间复杂度 O(nlogn)  空间复杂度  O(n)
	 * */
	private static void mergeSort(int[] arr){  // 方便调用
		int left = 0, right = arr.length-1;
		int[] helperArray = new int[arr.length];
		mymergeSort(arr, helperArray, left, right);
	}
	private static void mymergeSort(int[] arr, int[] helperArray, int left, int right){
		if(left >= right)
			return;
		int mid = left + (right - left) / 2;
		mymergeSort(arr, helperArray, left, mid);  //对左边部分进行排序
		mymergeSort(arr, helperArray, mid+1, right); // 对右边部分进行排序
		merge(arr, helperArray, left, mid, right); // 对左右两部分进行归并处理
	}
	/* 实现两个数组内元素的归并处理
	 * */
	private static void merge(int[] arr, int[] helperArray, int left, int mid, int right){
		//将元素分成两部分，左半部分 left -- mid， 右半部分 mid+1 -- right
		//i指向左半部分开始位置，j指向右半部分开始位置
		//  左半部分 [left, ..., mid] 		右半部分[mid+1, ... , right]
		int i = left, j = mid+1;
		//将arr中的元素拷贝到helperArray中
		System.arraycopy(arr, left, helperArray, left, right-left+1);
		for(int k = left; k <= right; k++){
			if(i > mid)  //左半部分用完，拷贝右半部分
				arr[k] = helperArray[j++];
			else if(j > right) //右半部分用完，拷贝左半部分
				arr[k] = helperArray[i++];
			else if(helperArray[i] < helperArray[j])  //左半部分某个元素 与右半部分某个元素比较，拷贝元素较值小的
				arr[k] = helperArray[i++];
			else if(helperArray[i] >= helperArray[j])
				arr[k] = helperArray[j++];
		}
	}
	
	/* 堆排序 ...
	 * 不稳定
	 * */
	
	/* 桶排序 ...
	 * 可以做到线性复杂度
	 * */
	
	/* 实现元素的交换
	 * */
	private static void swap(int[] arr, int l, int r){
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}
	
	/* 产生测试序列
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
		mergeSort(arr);  //选择排序的算法
		System.out.println(Arrays.toString(arr));
	}
}

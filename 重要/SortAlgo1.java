package sort;

public class SortAlgo {
	/* 交换相邻元素，每次只能消除一对逆序对，排序算法是稳定的
	 * */
	public static int[] bubbleSort(int[] arr){
		for(int i = 0;i < arr.length-1; i++){
			for(int j = 0;j < arr.length-i-1; j++){
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
	
	/* 插入排序，每次交换相邻元素，只能消除一对逆序对，是稳定的排序算法
	 * 对于逆序对很少的序列，插入排序效率很高
	 * */
	public static int[] insertSort(int[] arr){
		for (int i = 1; i < arr.length; i++) {  //第一个元素已经默认排好序了
			int ele = arr[i];
			int j = i;  // index i是待移动的元素
			for( ; j > 0 && arr[j-1] > ele; j--)
				arr[j] = arr[j-1];
			arr[j] = ele;
		}
		return arr;
	}
	
	/* 希尔排序，是一种基于插入排序的快速排序算法，每次交互不相邻的元素使序列部分有序
	 * 再通过插入排序对部分有序的序列进行排序，使每次交换尽可能多的消除逆序对
	 * 更小增量间隔的排序结果不会影响不会影响上次排序结果
	 * */
	public static int[] shellSort(int[] arr){
		for (int d = arr.length/2; d > 0; d /= 2) {  //定义增量序列
			for(int i = d; i < arr.length; i++){ //插入排序,注意这里是每次d间隔取元素
				int ele = arr[i];
				int j = i;
				for( ; j >= d && arr[j-d] > ele; j-=d)
					arr[j] = arr[j-d];
				arr[j] = ele;
			}
		}
		return arr;
	}
	
	
	/* 当前元素和当期元素之后的所有元素进行比较，选出一个最小的元素，如果最小值不是当前元素，则进行交换
	 * 因为是交换的不相邻元素(跳着交换)所以是不稳定的排序算法
	 * 根据getMinIndex不同的实现，算法有不同的时间复杂度，遍历实现， 最小堆实现
	 * */
	public static int[] selectSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			int minIndex = getMinIndex(arr, i);
			if(minIndex != i){
				int temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
		return arr;
	}
	private static int getMinIndex(int[] arr, int i){
		int minIndex = i;
		for(int j = i+1; j < arr.length; j++)
			if(arr[minIndex] > arr[j])
				minIndex = j;
		return minIndex;
	}
	
	/*快排*/
	public static void quickSort(int[] arr, int left, int right){
		// 基准选为中间位置，在排序过程中p来回跳变
		if(left >= right)
			return;
		int i = left, j = right, p = (left + right) / 2, pivot = arr[p];
		while(i != j){
			while(p < j && arr[j] >= pivot)
				j--;
			if(p < j){
				arr[p] = arr[j]; //将arr[j]的值赋予arr[p],arr[j]的元素成为空穴，等待填充
				p = j; //p指向空穴j的位置
			}
			while(i < p && arr[i] <= pivot)
				i++;
			if(i < p){
				arr[p] = arr[i];
				p = i;
			}
		}
		arr[i] = pivot;
		quickSort(arr, left, i-1);
		quickSort(arr, i+1, right);
	}
	
	public static void quickSort_1(int[] arr, int left, int right){
		//基准选在最左边
		if (left >= right)
			return;
		int i = left, j = right, pivot = arr[i];
		while(i != j){
			while(i < j && arr[j] >= pivot) j--;
			if(i < j) arr[i++] = arr[j];  
			while(i < j && arr[i] <= pivot) i++;
			if(i < j) arr[j--] = arr[i];
		}
		arr[i] = pivot;
		quickSort_1(arr, left, i-1);
		quickSort_1(arr, i+1, right);
	}
	
	public static void quickSort_2(int[] arr, int left, int right){
		if(left >= right) return;
		int i = partion(arr, left, right);  // 分
		quickSort_2(arr, left, i-1);  //治
		quickSort_2(arr, i+1, right); //治
	}
	private static int partion(int[] arr, int left, int right){
		int i = left, j = right, p = (left + right) / 2, pivot = arr[p];
		while(i != j){
			while(p < j && arr[j] >= pivot) j--;
			if(p < j){
				arr[p] = arr[j];
				p = j;
			}
			while(i < p && arr[i] <= pivot) i++;
			if(i < p){
				arr[p] = arr[i];
				p = i;
			}
		}
		arr[i] = pivot;
		return i;
	}
	
	/* 归并排序，对一个数组进行排序，先将数组分成两部分，分别对两部分进行排序，然后将排序完毕的两个
	 * 数组进行归并，分治思想
	 * */
	public static void mergeSort(int[] arr, int[] c, int left, int right){
		if(left >= right)
			return;
		int mid = (left + right) / 2;
		mergeSort(arr, c, left, mid);  //对左半部分进行排序 left -- mid
		mergeSort(arr, c, mid+1, right); //对右半部分进行排序 mid+1 -- right
		merge(arr, c, left, mid, right); //对排序完毕的左右部分进行归并
	}
	private static void merge(int[] arr, int[] c, int left, int mid, int right){
		// 实现数组的归并
		int i = left, j = mid+1;  
		//i和j分别是数组左右部分的开始的下标，将数组分成left -- mid, mid+1 -- right两部分
		// for(int k = left; k <= right; k++)
		// c[k] = arr[k];
		System.arraycopy(arr, left, c, left, right-left+1);  //实现元素的复制
		for(int k = left; k <= right; k++){
			if(i > mid)
				arr[k] = c[j++];
			else if(j > right)
				arr[k] = c[i++];
			else if(c[i] > c[j])
				arr[k] = c[j++];
			else if(c[i] <= c[j])
				arr[k] = c[i++];
		}
	}
	
	/* 堆排序
	 * */
	
	/* 桶排序
	 * */
}

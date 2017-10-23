package sort;

public class SortAlgo {
	/* ��������Ԫ�أ�ÿ��ֻ������һ������ԣ������㷨���ȶ���
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
	
	/* ��������ÿ�ν�������Ԫ�أ�ֻ������һ������ԣ����ȶ��������㷨
	 * ��������Ժ��ٵ����У���������Ч�ʺܸ�
	 * */
	public static int[] insertSort(int[] arr){
		for (int i = 1; i < arr.length; i++) {  //��һ��Ԫ���Ѿ�Ĭ���ź�����
			int ele = arr[i];
			int j = i;  // index i�Ǵ��ƶ���Ԫ��
			for( ; j > 0 && arr[j-1] > ele; j--)
				arr[j] = arr[j-1];
			arr[j] = ele;
		}
		return arr;
	}
	
	/* ϣ��������һ�ֻ��ڲ�������Ŀ��������㷨��ÿ�ν��������ڵ�Ԫ��ʹ���в�������
	 * ��ͨ����������Բ�����������н�������ʹÿ�ν��������ܶ�����������
	 * ��С�������������������Ӱ�첻��Ӱ���ϴ�������
	 * */
	public static int[] shellSort(int[] arr){
		for (int d = arr.length/2; d > 0; d /= 2) {  //������������
			for(int i = d; i < arr.length; i++){ //��������,ע��������ÿ��d���ȡԪ��
				int ele = arr[i];
				int j = i;
				for( ; j >= d && arr[j-d] > ele; j-=d)
					arr[j] = arr[j-d];
				arr[j] = ele;
			}
		}
		return arr;
	}
	
	
	/* ��ǰԪ�غ͵���Ԫ��֮�������Ԫ�ؽ��бȽϣ�ѡ��һ����С��Ԫ�أ������Сֵ���ǵ�ǰԪ�أ�����н���
	 * ��Ϊ�ǽ����Ĳ�����Ԫ��(���Ž���)�����ǲ��ȶ��������㷨
	 * ����getMinIndex��ͬ��ʵ�֣��㷨�в�ͬ��ʱ�临�Ӷȣ�����ʵ�֣� ��С��ʵ��
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
	
	/*����*/
	public static void quickSort(int[] arr, int left, int right){
		// ��׼ѡΪ�м�λ�ã������������p��������
		if(left >= right)
			return;
		int i = left, j = right, p = (left + right) / 2, pivot = arr[p];
		while(i != j){
			while(p < j && arr[j] >= pivot)
				j--;
			if(p < j){
				arr[p] = arr[j]; //��arr[j]��ֵ����arr[p],arr[j]��Ԫ�س�Ϊ��Ѩ���ȴ����
				p = j; //pָ���Ѩj��λ��
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
		//��׼ѡ�������
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
		int i = partion(arr, left, right);  // ��
		quickSort_2(arr, left, i-1);  //��
		quickSort_2(arr, i+1, right); //��
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
	
	/* �鲢���򣬶�һ��������������Ƚ�����ֳ������֣��ֱ�������ֽ�������Ȼ��������ϵ�����
	 * ������й鲢������˼��
	 * */
	public static void mergeSort(int[] arr, int[] c, int left, int right){
		if(left >= right)
			return;
		int mid = (left + right) / 2;
		mergeSort(arr, c, left, mid);  //����벿�ֽ������� left -- mid
		mergeSort(arr, c, mid+1, right); //���Ұ벿�ֽ������� mid+1 -- right
		merge(arr, c, left, mid, right); //��������ϵ����Ҳ��ֽ��й鲢
	}
	private static void merge(int[] arr, int[] c, int left, int mid, int right){
		// ʵ������Ĺ鲢
		int i = left, j = mid+1;  
		//i��j�ֱ����������Ҳ��ֵĿ�ʼ���±꣬������ֳ�left -- mid, mid+1 -- right������
		// for(int k = left; k <= right; k++)
		// c[k] = arr[k];
		System.arraycopy(arr, left, c, left, right-left+1);  //ʵ��Ԫ�صĸ���
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
	
	/* ������
	 * */
	
	/* Ͱ����
	 * */
}

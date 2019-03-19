package BLL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.*;

public class Main {

	// 功能1：统计txt文件中单词频率并输出到result.txt文件中
	static void countTextWords() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"H:\\WordCount\\wwh\\src\\date.txt"));
			List<String> lists = new ArrayList<String>(); // 存储过滤后单词的列表
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				String[] wordsArr1 = readLine.split("[^a-zA-Z]"); // 过滤出只含有字母的
				for (String word : wordsArr1) {
					if (word.length() != 0) { // 去除长度为0的行
						lists.add(word);
					}
				}
			}

			br.close();

			Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); // 存储单词计数信息，key值为单词，value为单词数

			// 单词的词频统计
			for (String li : lists) {
				if (wordsCount.get(li) != null) {
					wordsCount.put(li, wordsCount.get(li) + 1);
				} else {
					wordsCount.put(li, 1);
				}
			}
			SortMap(wordsCount); // 按值进行排序
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 按value的大小进行排序
	public static void SortMap(Map<String, Integer> oldmap) {

		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				oldmap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue(); // 降序
			}
		});

		File f = new File("H:\\WordCount\\wwh\\src\\result.txt");

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(f));

			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i).getKey() + ": "
//						+ list.get(i).getValue());
				bw.write(list.get(i).getKey() + ":" + list.get(i).getValue());
				bw.newLine();
			}

			bw.flush();
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("统计结果已输入到result.txt文件中！");

		// 将统计结果放入result.txt文件

	}

	static void checkword() {

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"H:\\WordCount\\wwh\\src\\result.txt"));
			List<String> list = new ArrayList<String>();
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				if (readLine.trim().length() > 2) {
					list.add(readLine);				
				}
			}
			br.close();

			System.out.println("请输入要查询的单词：");
			Scanner sc = new Scanner(System.in);
			String word = sc.next();
			
			int flag = 0;
			int t = -1;
			// 遍历集合查询单词
			for (int i = 0; i < list.size(); i++) {
				
				String[] k = list.get(i).split(":");
				if (k[0].equals(word)){ 					
					flag=1;		
					t=i;
					break;
				}				
				else {
					flag=0;
				}
			
			}
			if(flag==1){
				System.out.println(list.get(t));
			}else{
				System.out.println("文件中没有该单词！");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//功能3：输出指定排名的单词
	static void statisticsword() {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("H:\\WordCount\\wwh\\src\\result.txt"));
		
		List<String> list = new ArrayList<String>();
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
			if (readLine.trim().length() > 2) {
				list.add(readLine);
			}

		}

		br.close();
		
		System.out.println("您查询频率为前多少的单词？");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			
			  String[] k = list.get(i).split(":");
			  int x =  Integer.parseInt(k[1]);
		      System.out.print(list.get(i)+"|");
		      for(int j=0;j<x;j++){
		    	  System.out.print("★");
		      }
		      System.out.println();
				
				
		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) throws IOException {
 
		System.out.println("****************功能菜单***************");
		System.out.println("* 选项1--统计词频                                       *");
		System.out.println("* 选项2--统计指定单词词频                         *");
		System.out.println("* 选项3--查询频率最高的前n个单词             *");
		System.out.println("***************************************");

		System.out.println("请输入：");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		switch (n) {
		case 1:
			countTextWords();
			break;
		case 2:
			checkword();
			break;
		case 3:
			statisticsword();
			break;
		default:
			System.out.println("输入有误！！");
			break;
		}
	}

}

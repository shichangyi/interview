package com.study.interview.java200;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

public class Day0614 {
	// 17. Files的常用方法都有哪些？
	@Test
	public void test17() throws IOException {
		Path path = Paths.get("D:\\", "xxx");
		// Files.createFile(path);

		// Files. exists()：检测文件路径是否存在。
		if (Files.exists(path)) {
			Files.delete(path);
			System.out.println(path.toAbsolutePath().toString());
		}

		// Files. createFile()：创建文件。
		Files.createFile(path);

		// Files. createDirectory()：创建文件夹。
		// Files. delete()：删除一个文件或目录。
		// Files. copy()：复制文件。
		// Files. move()：移动文件。
		// Files. size()：查看文件个数。
		// Files. read()：读取文件。
		// Files. write()：写入文件。
	}

	// 18. Java 容器都有哪些？
	@org.junit.Test
	public void test18() {
		// Collection
		// --List
		// ----ArrayList
		// ----LinkedList
		// --Vector
		// --Stack
		// --Set
		// ----HashSet : 按照最小堆对数据进行升序排序
		// ----LinkedHashSet : 按照插入顺序访问以元素
		// ----TreeSet : 按照最小堆对数据进行升序排序
		// Map
		// --HashMap
		// --LinkedHashMap
		// --TreeMap
		// --ConcurrentHashMap
		// --Hashtable
		/*
		 * Set<Integer> s = buildSet(1 * 10000); for(Integer id : s) {
		 * //System.err.print(id + " "); }
		 */
		// System.out.println(s.size());

		// HashMap & Hashtable & ConcurrentHashMap & TreeMap & HashMap
		//testMap();
		
		String obj[] = {"xx","dd"};
		String b1[] = {"x","t","111"};
		Arrays.asList(obj).toArray(b1);
		System.out.println(Arrays.asList(b1));

	}

	private void testMap() {
		// HashMap & Hashtable & ConcurrentHashMap & TreeMap

		Map<Integer, Integer> table = null;
		int n = 100 * 10000;

		// insert test
		// table = new Hashtable<Integer, Integer>();
		// insert(table, n);
		// table = new Hashtable<Integer, Integer>();
		// insert(table, n);
		// table = new Hashtable<Integer, Integer>();
		// insert(table, n);
		System.out.println("\nHashtable insert");
		for (int i = 0; i < 10; i++) {
			table = new Hashtable<Integer, Integer>();
			insert(table, n);
		}

		System.out.println("\nHashMap insert");
		for (int i = 0; i < 10; i++) {
			table = new HashMap<Integer, Integer>();
			insert(table, n);
		}

		System.out.println("\nConcurrentHashMap insert");
		for (int i = 0; i < 10; i++) {
			table = new ConcurrentHashMap<Integer, Integer>();
			insert(table, n);
		}
		System.out.println("\nTreeMap insert");
		for (int i = 0; i < 10; i++) {
			table = new TreeMap<Integer, Integer>();
			insert(table, n);
		}

	}

	private void insert(Map<Integer, Integer> map, int n) {
		Long t1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			Integer key = i * 31 % n;
			map.put(key, key);
		}
		Long t2 = System.currentTimeMillis();
		System.out.print(t2 - t1 + " ");
	}

	private Set<Integer> buildSet(int n) {
		Set<Integer> set = new HashSet<>(); // 3851 - 7872887
		// Set<Integer> set = new LinkedHashSet<>(); // 3874 - 7872887
		// Set<Integer> set = new TreeSet(); // 8969 - 7872887
		Long t1 = System.currentTimeMillis();
		for (Integer id = 0; id < n; id++) {
			int s = n - id;
			if (id == n - 1) {
				System.out.println(id);
			}
			set.add(s);
			// System.out.println(set);
		}
		set.add(3);
		System.out.println(set);
		Long t2 = System.currentTimeMillis();
		System.out.print(t2 - t1 + " ");
		return set;
	}

	private volatile int v = 0;

	@org.junit.Test
	public void test11() throws InterruptedException {
		int n = 10;
		long t = System.currentTimeMillis();
		CountDownLatch latch = new CountDownLatch(n);
		for (int i = 0; i < n; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						incr();
					}
					latch.countDown();
				}
			}).start();
		}

		latch.await();
		t = System.currentTimeMillis() - t;
		System.out.println(t + " ms , v = " + v);
	}

	public void incr() {
		while (true) {
			int before = v;
			v = v + 1;
			int end = v;
			if (end - before == 1) {
				break;
			}
			// System.err.println("冲突");
		}

	}
}

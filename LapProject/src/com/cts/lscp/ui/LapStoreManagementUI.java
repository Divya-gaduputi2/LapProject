package com.cts.bscp.ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cts.bscp.exception.LAPStoreException;
import com.cts.bscp.model.Lap;
import com.cts.bscp.model.LapStoreAppMenu;


import com.cts.bscp.service.ILapService;

public class LapStoreManagementUI {

	private static ILapService lapService;
	private static Scanner scan;
	private static DateTimeFormatter dtFormater;

	public static void main(String[] args) {

		scan = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LapStoreAppMenu menu = null;

		while (menu != LapStoreAppMenu.QUIT) {

			System.out.println("Choice\tMenu Item");
			System.out.println("===========================");
			for (LapStoreAppMenu m : LapStoreAppMenu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			System.out.print("Choice: ");
			int choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			else {
				scan.next();
				System.out.println("Pleae choose a choice displayed");
				continue;
			}

			if (choice < 0 || choice >= LapStoreAppMenu.values().length) {
				System.out.println("Invalid Choice");
			} else {
				menu = LapStoreAppMenu.values()[choice];
				switch (menu) {
				case ADD:
					doAdd();
					break;
				case LIST:
					doList();
					break;
				case SEARCH:
					doSearch();
					break;
				case REMOVE:
					doRemove();
					break;
				case QUIT:
					System.out.println("ThanQ Come Again!");
					break;
				}
			}

		}

		scan.close();

	}

	private static void doAdd() {
		try {
			Lap Lap = new Lap();
			System.out.print("lapcode: ");
			Lap.setlapcode(scan.next());
			System.out.print("Title: ");
			Lap.setCompanytitle(scan.next());
			System.out.print("PublishDate(dd-MM-yyyy): ");
			String pubDtStr = scan.next();

			try {
				Lap.setReleaseDate(LocalDate.parse(pubDtStr, dtFormater));
			} catch (DateTimeException exp) {
				throw new LAPStoreException(
						"Date must be in the format day(dd)-month(MM)-year(yyyy)");
			}
			System.out.print("Price: ");
			if (scan.hasNextDouble())
				Lap.setPrice(scan.nextDouble());
			else {
				scan.next();
				throw new LAPStoreException("Price is a number");
			}

			String lapcode = lapService.add(Lap);
			System.out.println("Lapis Added with code: " + lapcode);
		} catch (LAPStoreException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doList() {
		List<Lap> Laps;
		try {
			Laps = lapService.getAll();
			if (Laps.size() == 0) {
				System.out.println("No Laps To display");
			} else {
				for (Lap b : Laps)
					System.out.println(b);
			}
		} catch (LAPStoreException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doSearch() {
		System.out.print("lapcode: ");
		String lapcode = scan.next();

		try {
			Lap Lap = lapService.get(lapcode);
			if (Lap != null) {
				System.out.println(Lap);
			} else {
				System.out.println("No Such Lap");
			}
		} catch (LAPStoreException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doRemove() {
		System.out.print("lapcode: ");
		String lapcode = scan.next();
		try {
			boolean isDone = lapService.delete(lapcode);
			if (isDone) {
				System.out.println("Lapis Deleted");
			} else {
				System.out.println("No Such Lap");
			}
		} catch (LAPStoreException exp) {
			System.out.println(exp.getMessage());
		}
	}
}

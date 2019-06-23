package question12;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 猫狗队列问题，实质上还是使用两个队列，只不过又封装了一个类，这个类中记录着每个pet进入各自队列的顺序
 * @author CGWEI
 *
 */

public class Solution {
	private static class PetCounter{
		private Pet pet;
		private static int flag;
		private final int count = ++flag;
		public PetCounter(Pet pet) {
			this.pet = pet;
		}
		public int getCount() {
			return count;
		}
		public Pet getPet() {
			return pet;
		}
	}
	private static class CatDogQueue{
		private Queue<PetCounter> cats;
		private Queue<PetCounter> dogs;
		public CatDogQueue() {
			cats = new LinkedList<>();
			dogs = new LinkedList<>();
		}
		public void addCat(Cat cat) {
			cats.add(new PetCounter(cat));
		}
		public void addDog(Dog dog) {
			dogs.add(new PetCounter(dog));
		}
		public void addPet(Pet pet) {
			if(pet.getClass() == Dog.class) {
				addDog((Dog)pet);
			}
			else if(pet.getClass() == Cat.class) {
				addCat((Cat)pet);
			}
		}
		public Cat pollCat() {
			if(cats.isEmpty()) {
				throw new RuntimeException("No cat in the queue.");
			}
			return (Cat)cats.poll().getPet();
		}
		public Dog pollDog() {
			if(dogs.isEmpty()) {
				throw new RuntimeException("No dog in the queue.");
			}
			return (Dog)dogs.poll().getPet();
		}
		public Pet pollPet() {
			if(cats.isEmpty() && dogs.isEmpty()) {
				throw new RuntimeException("No pet in the queue.");
			}
			else if(!cats.isEmpty() && dogs.isEmpty()){
				return cats.poll().getPet();
			}
			else if(!dogs.isEmpty() && cats.isEmpty()) {
				return dogs.poll().getPet();
			}
			else {
				return dogs.peek().getCount() < cats.peek().getCount() ? dogs.poll().getPet() : cats.poll().getPet();
			}
		}
	}
	
	public static void main(String[] args) {
		CatDogQueue catDogQueue = new CatDogQueue();
		catDogQueue.addCat(new Cat());
		catDogQueue.addDog(new Dog());
		
		System.out.println(catDogQueue.pollCat().getPetType());
		System.out.println(catDogQueue.pollPet().getPetType());
	}
}

class Pet {
	private String type;
	public Pet(String type) {
		this.type = type;
	}
	public String getPetType() {
		return this.type;
	}
}
class Dog extends Pet {
	public Dog() {
		super("dog");
	}
}
class Cat extends Pet {
	public Cat() {
		super("cat");
	}
}



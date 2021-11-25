import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import res.HibernateSessionFactory;
import res.IssuedBooksEntity;
import res.ReturnedBooksEntity;
import res.StudentsEntity;

import javax.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(final String[] args)  {
        ArrayList<IssuedBooksEntity> list = (ArrayList<IssuedBooksEntity>) findAll();
        ArrayList<ReturnedBooksEntity> list1 = (ArrayList<ReturnedBooksEntity>) findAllL();
        for(IssuedBooksEntity entity : list){
            System.out.println(entity.getId() + " " + entity.getId_book() + " " + entity.getId_student() + " " + entity.getData());
        }
        System.out.println(getEvilStudent(list,list1));
    }
    public static List<IssuedBooksEntity> findAll(){
        return HibernateSessionFactory.getSessionFactory().openSession().createQuery("FROM IssuedBooksEntity", IssuedBooksEntity.class).getResultList();
    }
    public static List<ReturnedBooksEntity> findAllL(){
        return HibernateSessionFactory.getSessionFactory().openSession().createQuery("FROM ReturnedBooksEntity ", ReturnedBooksEntity.class).getResultList();
    }

    public static String getEvilStudent(ArrayList<IssuedBooksEntity> issued, ArrayList<ReturnedBooksEntity> returned){
        ArrayList<StudentsEntity> studentsEntities =
                (ArrayList<StudentsEntity>)
                        HibernateSessionFactory.getSessionFactory().openSession()
                                .createQuery("FROM StudentsEntity ", StudentsEntity.class).getResultList();
        int[] maxCount = maxCount(issued,studentsEntities.size());//подсчитывается количество вхождений каждого студента в таблице выдачи результат -
        int[] maxCount1 = maxCount1(returned, studentsEntities.size());
        int[] result = new int[maxCount1.length];

        for(int i = 0; i < result.length; i++){
           result[i] = maxCount[i] - maxCount1[i];
        }
        int max = 0;
        int index = 0;
        for(int i = 0; i < result.length; i++){
            if(result[i] >= max){
                max = result[i];
                index = i;
            }
        }
        return  studentsEntities.get(index).getName();
    }

    static int[] maxCount(ArrayList<IssuedBooksEntity> issued, int studSize){
        int[] arr = new int[studSize];
        for(int i = 0; i < issued.size(); i++){
            int user = 0;
            for(int j = 0; j < issued.size(); j++){
                if(issued.get(i).getId_student() == issued.get(j).getId_student()){
                    user++;
                    arr[issued.get(i).getId_student()-1] = user;
                }
            }
        }
        return arr;
    }
    static int[] maxCount1(ArrayList<ReturnedBooksEntity> returned, int studSize){
        int[] arr = new int[studSize];
        for(int i = 0; i < returned.size(); i++){
            int user = 0;
            for(int j = 0; j < returned.size(); j++){
                if(returned.get(i).getId_student() == returned.get(j).getId_student()){
                    user++;
                    arr[returned.get(i).getId_student()-1] = user;
                }
            }
        }
        return arr;
    }

}
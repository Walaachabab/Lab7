package com.udemycourse.springboot.library.Service;


import com.udemycourse.springboot.library.Model.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MemberService {
    ArrayList<Member> members = new ArrayList<>();

    // GET
    public ArrayList<Member> getAll() {
        return members;
    }

    // ADD
    public void addMember(Member member) {
        members.add(member);
    }

    // UPDATE
    public boolean update(String id, Member member) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(id)) {
                member.setId(id);
                members.set(i, member);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(String id) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(id)) {
                members.remove(i);
                return true;
            }
        }
        return false;
    }

    // LOGIC
    public ArrayList<Member> getMembersWithBorrowedBooks() {
        ArrayList<Member> result = new ArrayList<>();
        for (Member m : members) {
            if (m.getBorrowedBooks() > 0) {
                result.add(m);
            }
        }
        return result;
    }
}

package stream.api.code.kata;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import stream.api.code.kata.utils.ClassicOnlineStore;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Exercise3Test extends ClassicOnlineStore {

    @Test
    public void howManyItemsWanted() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Count the sum of how many items there are in {@link Customer.wantToBuy} using {@link Stream#count}
         */
        long sum = customerList.stream().flatMap(e -> e.getWantToBuy().stream()).count();

        assertThat(sum, is(32L));
    }

    @Test
    public void richestCustomer() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Find the richest customer's amount of money using {@link Stream#max} and {@link Comparator#naturalOrder}
         * Don't use {@link Stream#sorted}
         */
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Optional<Integer> richestCustomer = customerList.stream().map(Customer::getMoney).max(comparator);

        assertThat(comparator.getClass().getSimpleName(), is("NaturalOrderComparator"));
        assertThat(richestCustomer.get(), is(12000));
    }

    @Test
    public void youngestCustomer() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Find the youngest customer using {@link Stream#min}
         * Don't use {@link Stream#sorted}
         */
        Comparator<Customer> comparator = (o1, o2) -> o1.getAge() - o2.getAge();;
        Optional<Customer> youngestCustomer = customerList.stream().min(comparator);

        assertThat(youngestCustomer.get(), is(customerList.get(8)));
    }
}

package com.demo.softdreams.shared.common;


public class QueryConstance {

    private QueryConstance() {
    }

    public static class CommonQuery {
        private CommonQuery() {
        }

        public static final String ORDER_BY = "order by {0}";
        public static final String PAGEABLE = "limit {0} offset {1}";
    }

    public static class Report {

        private Report() {
        }

        public static class UserRp {

            private UserRp() {
            }


            public static class BlogRp {

                private BlogRp() {
                }

                public static final String SUBSCRIPTION = "with gs as (\n " +
                        "\tselect\n " +
                        "\t\tdate_trunc(:truncType, dt) as reportDate,\n " +
                        "\t\t0 as totalNewSubBought\n " +
                        "\tfrom generate_series(:startTs\\:\\:date, :endTs\\:\\:date, ('1 ' || :truncType)\\:\\:interval) dt),\n " +
                        "tsb as (\n " +
                        "select\n " +
                        "\tcount(sl.id) filter(where sl.created_date <= gs.reportDate and sl.deleted = false) as totalSubBoughtLastDay,\n " +
                        "\tgs.reportDate as reportDate\n " +
                        "from\n " +
                        "\tsubscription_log sl,\n " +
                        "\tgs\n " +
                        "where\n " +
                        "\tsl.deleted = false\n " +
                        "\tand sl.status = 2\n " +
                        "\tand (sl.\"action\" = '1' or sl.\"action\" = '0')\n " +
                        "\tand (cast(:subId as varchar) is null or cast(sl.sub_id as varchar) = cast(:subId as varchar))\n " +
                        "group by gs.reportDate),\n " +
                        "tnsb as (\n " +
                        "select\n " +
                        "\tdate_trunc(:truncType, sl.created_date at time zone 'Asia/Ho_Chi_Minh') as reportDate,\n " +
                        "\tcount(sl.id) as totalNewSubBought\n " +
                        "from\n " +
                        "\tsubscription_log sl\n " +
                        "where\n " +
                        "\tsl.deleted = false\n " +
                        "\tand sl.status = 2\n " +
                        "\tand (sl.\"action\" = '1' or sl.\"action\" = '0')\n " +
                        "\tand (cast(:subId as varchar) is null or cast(sl.sub_id as varchar) = cast(:subId as varchar))\n " +
                        "\tand (sl.created_date between :startTs and :endTs)\n " +
                        "\tgroup by 1),\n " +
                        "n as (\n " +
                        "select gs.* from gs left join tnsb on gs.reportDate = tnsb.reportDate where tnsb.reportDate is null\n " +
                        "union\n " +
                        "select tnsb.* from tnsb)\n " +
                        "select\n " +
                        "\tcast(extract (epoch from tsb.reportDate) * 1000 as int8) as reportDate,\n " +
                        "\tcast(sum(tsb.totalSubBoughtLastDay + n.totalNewSubBought) as int8) as totalSubBought,\n " +
                        "\tn.totalNewSubBought as totalNewSubBought\n " +
                        "from\n " +
                        "\ttsb\n " +
                        "right join n on tsb.reportDate = n.reportDate\n " +
                        "group by\n " +
                        "\ttsb.reportDate,\n " +
                        "\tn.totalNewSubBought,\n " +
                        "\ttsb.totalSubBoughtLastDay\n ";

                public static final String TOTAL_SUB_WHEN_FILTER = " select\n " +
                        "\tcast(s.id as varchar) as subId,\n " +
                        "\ts.sub_name || ' (' || s.sub_code || ')'as subName\n " +
                        "from\n " +
                        "\t\"subscription\" s\n " +
                        "where\n " +
                        "\ts.deleted = false\n " +
                        "order by\n " +
                        "\t2\n ";

            }
        }
    }
}

create table if not exists chat_link
(
    chatId bigint references chat (id),
    linkId bigint references link (id),

    primary key (chatId, linkId)
);

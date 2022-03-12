

CREATE TABLE schema_data_version (
  version_rank integer NOT NULL,
  installed_rank integer NOT NULL,
  version character varying(50) NOT NULL,
  description character varying(200) NOT NULL,
  type character varying(20) NOT NULL,
  script character varying(1000) NOT NULL,
  checksum integer,
  installed_by character varying(100) NOT NULL,
  installed_on timestamp without time zone NOT NULL DEFAULT now(),
  execution_time integer NOT NULL,
  success boolean NOT NULL,
  CONSTRAINT schema_data_version_pk PRIMARY KEY (version)
);

CREATE INDEX schema_data_version_ir_idx ON schema_data_version USING btree (installed_rank);
CREATE INDEX schema_data_version_s_idx  ON schema_data_version USING btree (success);
CREATE INDEX schema_data_version_vr_idx ON schema_data_version USING btree (version_rank);



insert into schema_data_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) values (1, 1, 1, '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', null, 'postgres', '2016-08-15 09:33:44.566255', 0, true);
